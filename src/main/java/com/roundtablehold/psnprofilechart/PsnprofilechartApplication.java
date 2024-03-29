package com.roundtablehold.psnprofilechart;

import com.gargoylesoftware.htmlunit.WebClient;
import com.roundtablehold.psnprofilechart.business.ProfileScraperService;
import com.roundtablehold.psnprofilechart.data.PSNProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class PsnprofilechartApplication {


	public static void main(String[] args) {
		SpringApplication.run(PsnprofilechartApplication.class, args);
	}




}
