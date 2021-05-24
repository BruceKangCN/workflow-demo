# 教程

本篇教程将带你学习如何利用`Java`以及`camunda`、`quartz`、`freemarker`框架搭建基于**工作流引擎**和**代码生成器**的低代码CI/CD后端

## 准备工作

1. 进入 [srping initializr](https://start.spring.io) 创建`maven`项目，语言选择`Java`，Spring Boot版本选择`2.3.x`，设定项目元信息，打包方式选择`jar`，Java版本选择`8`，添加`H2 Database`、`Quartz Scheduler`、`Apache Freemarker`依赖，点击`GENERATE`生成项目并下载到本地
2. 使用IDE打开项目，在`pom.xml`中手动添加`camunda`依赖
   ```xml
   <!-- 在properties标签内添加以下内容 -->
   <camunda.spring-boot.version>7.15.0</camunda.spring-boot.version>
   <!-- 在dependencies标签内添加以下内容 -->
   <dependency>
   <groupId>org.camunda.bpm.springboot</groupId>
   <artifactId>camunda-bpm-spring-boot-starter-webapp</artifactId>
   <version>${camunda.spring-boot.version}</version>
   </dependency>
   <dependency>
   <groupId>com.sun.xml.bind</groupId>
   <artifactId>jaxb-impl</artifactId>
   <version>2.2.3</version>
   </dependency>
   ```
3. 下载依赖并构建，测试是否可运行
4. 下载 [Camunda Modeler](https://camunda.com/download/modeler/) 并解压，待后续使用

## `camunda`工作流

### 配置账户

1. 在资源根目录添加`application.yml`，添加以下内容
   ```yaml
   camunda.bpm:
     admin-user:
       id: demo
       password: demo
       firstName: Demo
     filter:
       create: All tasks
   ```
   这将创建一个名为`Demo`的管理员，账户ID为`demo`，账户密码为`demo`，并且创建资源目录下所有`**/*.bpmn`定义的任务  
   > 值得注意的是在该文件中并不需要配置数据源，因为本教程使用`H2`嵌入式内存数据库  
2. 运行`main`方法，访问[控制台](http://localhost:8080)，以定义好的ID、密码登录，之后，便可以在**Tasklist**中看到一个用来显示所有任务的**All tasks**过滤器

### 创建流程模型

1. 打开`Camunda Modeler`，创建一个如下的流程模型
   ![loan approval](img/loanApproval.png)
   > 提示：如果你不熟悉如何创建流程模型，可以访问[该教程](https://docs.camunda.org/get-started/quick-start/service-task/)  

2. 将该流程模型保存为资源目录下的`processes/loanApproval.bpmn`
3. 在主类上添加`@EnableProcessApplication`注解，这将创建流程应用
4. 在`<basePackage>.process.LoanApproval`类中添加以下代码，这将使`loanApproval`流程实例被部署后自动执行
   ```java
   package org.bkcloud.fleet.workflow.process;
   
   import org.camunda.bpm.engine.RuntimeService;
   import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.context.event.EventListener;
   import org.springframework.stereotype.Component;
   
   @Component
   public class LoanApproval {
   
       @Autowired
       private RuntimeService runtimeService;
   
       @EventListener
       public void processPostDeploy(PostDeployEvent event) {
           runtimeService.startProcessInstanceByKey("loanApproval");
       }
   }
   ```
5. 之后，重新启动应用，可以在**All tasks**过滤器下看到`loanApproval`被启动
   ![tasklist](img/tasklist.png)

## `quartz`作业调度器

### 配置调度器

在资源目录下的`application.yml`中添加以下内容
```yaml
spring:
  quartz:
    job-store-type: jdbc
    properties.org.quartz:
      scheduler:
        instanceId: AUTO
      threadPool:
        threadCount: 4
```

### 添加作业

在`<basePackage>.job.Greet`类中添加以下代码
```java
package org.bkcloud.fleet.workflow.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Greet implements Job {
   @Override
   public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
      System.out.println("Hello world!");
   }
}
```

### 配置作业、触发器

在`<basePackage>.config.QuartzConfiguration`类中添加以下代码
```java
package org.bkcloud.fleet.workflow.config;

import org.bkcloud.fleet.workflow.job.Greet;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfiguration {

   @Bean
   public JobDetail jobDetail() {
      return JobBuilder.newJob(Greet.class).withIdentity("demo job").storeDurably().build();
   }

   @Bean
   public Trigger jobTrigger(JobDetail jobDetail) {
      return TriggerBuilder.newTrigger().forJob(jobDetail).withIdentity("demo trigger")
              .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * ? * * *"))
              .build();
   }
}
```
`jobDetail`将为`Greet`作业配置详细的设定，之后`jobTrigger`将以`jobDetail`中的配置配置触发器，并以**Cron表达式**指定该触发器每10秒触发一次

> #### Cron表达式  
> 以7个值构成，从左到右依次为秒、分、时、日、月、一周中的第几天、年，最后一个参数可选，以空格分割  
> 可以用`*`代表所有值，`-`指定范围，`?`表示忽略，`,`分割多个值  
> 可以用`/`分割数字指定初始值和每次递增的值  
> 可以用`L`在**日**字段指定本月最后一天，在**周**字段指定周六  
> 可以用数字+`L`在**周**字段指定本月最后一个周几  
> 可以在**日**字段用数字+`W`指定本月最接近该日期的工作日，或用`LW`指定本月最后一个工作日  
> 可以用`#`分割数字在**周**字段指定本月周几中的第几个  
> 可以用数字+`C`在**日**和**周**字段指定该天或之后第一次包含任务的那天  

### 执行

执行`main`方法，待应用启动完成后，便可以看到控制台中每10秒打印一行`Hello world!`
