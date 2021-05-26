# README

This is a tutorial for building a low-code CI/CD back-end based on **workflow engine** and **code generator** using `Java` with `spring-boot`, `camunda`, `quartz`, `freemarker`

## API

### **`GET`** `/` or `/camunda/app`

camunda web app login page, id and password are set in `application.yml`

#### Parameters

#### Response

`html`: camunda web app login page

### **`GET`** `/process/count`

display the count of all process instance deployed by `camunda`

#### Parameters

#### Response

`long`: count of all process instances deployed by `camunda`

### **`GET`** `/process/list`

display a list of all process instances information

#### Parameters

#### Response

`List`: a list of all process instances information

### **`GET`** `/city/all`

display information of all cities in database

#### Parameters

#### Response

`html`: a table of all the cities stored in database, rendered by `freemarker`

### **`POST`** `/run-script/create`

create a run script process

#### Parameter

`{"source": string}`: a JSON object with the source code of the script

#### Response

`{"id": string, "source": string}`: a JSON object with the UUID of created process and the source code of the script

### **`POST`** `/run-script/deploy`

deploy the run script process

#### Parameter

`{"id": string}`: a JSON object with the UUID of the process

#### Response

### **`POST`** `/run-script/start`

start a run script process instance

#### Parameter

#### Response

## Requirements

* `JDK 8`
* `maven`
* IDE, `Intellij Idea` is recommended
* `Camunda Modeler`

## Credits

This demo is powered by the frameworks below, sorted by license type

### Apache 2.0

* spring-boot
* undertow
* camunda
* quartz
* freemarker

### MPL 2.0

* h2

### Eclipse Public License - v 2.0

* junit5
