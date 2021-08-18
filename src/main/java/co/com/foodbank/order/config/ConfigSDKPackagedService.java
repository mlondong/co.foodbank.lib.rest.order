package co.com.foodbank.order.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import co.com.foodbank.packaged.sdk.service.SDKPackagedService;

/**
 * Bean Configuration for SDKPackagedService.
 * 
 * @author mauricio.londono@gmail.com co.com.foodbank.order.config 14/08/2021
 */
@Configuration
@Qualifier("sdkPackaged")
public class ConfigSDKPackagedService {

    @Bean
    public SDKPackagedService sdkPackaged() {
        return new SDKPackagedService();
    }
}
