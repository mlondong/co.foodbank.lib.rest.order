package co.com.foodbank.order.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import co.com.foodbank.message.sdk.service.SDKMessageService;

/**
 * Bean Configuration for SDKMessageService.
 * 
 * @author mauricio.londono@gmail.com co.com.foodbank.order.config 14/08/2021
 */
@Configuration
@Qualifier("sdkMessage")
public class ConfigSDKMessageService {

    @Bean
    public SDKMessageService sdkMessage() {
        return new SDKMessageService();
    }
}
