package co.com.foodbank.order.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import co.com.foodbank.user.sdk.service.SDKUserService;

/**
 * Bean Configuration for SDKUserService.
 * 
 * @author mauricio.londono@gmail.com co.com.foodbank.order.config 11/08/2021
 */
@Configuration
@Qualifier("sdkUser")
public class ConfigSDKUserService {
    @Bean
    public SDKUserService sdkUser() {
        return new SDKUserService();
    }
}
