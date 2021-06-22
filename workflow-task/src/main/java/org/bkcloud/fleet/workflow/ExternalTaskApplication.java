package org.bkcloud.fleet.workflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ExternalTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExternalTaskApplication.class, args);
    }
}
