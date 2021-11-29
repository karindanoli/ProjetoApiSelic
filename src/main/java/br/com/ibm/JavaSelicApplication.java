package br.com.ibm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = "br.com.ibm")
@EntityScan(basePackages = "br.com.ibm.entities")
public class JavaSelicApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaSelicApplication.class, args);
    }
}
