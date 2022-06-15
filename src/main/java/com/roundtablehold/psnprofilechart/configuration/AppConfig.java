package com.roundtablehold.psnprofilechart.configuration;

import com.gargoylesoftware.htmlunit.WebClient;
import com.roundtablehold.psnprofilechart.business.ProfileScraperService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public WebClient webClient(){
        return new WebClient();
    }

    @Bean
    public ProfileScraperService profileScraperService(){
        return new ProfileScraperService(webClient());
    }

}
