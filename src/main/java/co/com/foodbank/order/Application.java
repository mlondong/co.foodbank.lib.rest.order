package co.com.foodbank.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import co.com.foodbank.message.sdk.config.EnableMessageSDK;
import co.com.foodbank.user.sdk.config.EnableUserSDK;

@EnableUserSDK
@EnableMessageSDK
@ComponentScan({"co.com.foodbank.order", "co.com.foodbank.order.exception",
        "co.com.foodbank.order.repository",
        "co.com.foodbank.order.restcontroller", "co.com.foodbank.order.config",
        "co.com.foodbank.order.service", "co.com.foodbank.order.v1.controller",
        "co.com.foodbank.order.v1.model", "co.com.foodbank.order.util"})
@SpringBootApplication
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
