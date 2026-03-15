package com.parking.centers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.actuate.info.MapInfoContributor;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@SpringBootApplication
@EnableJpaRepositories
public class CentersApplication {

	public static void main(String[] args) {
        SpringApplication.run(CentersApplication.class, args);
	}

    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        Locale rus = new Locale("ru", "RU");
        localeResolver.setDefaultLocale(rus);
        return localeResolver;
    }

    @Bean
    public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setBasenames("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    InfoContributor getInfoContributor() {
        Map<String, Object> details = new HashMap<>();
        details.put("nameApp", "ParkingSystem");
        details.put("developer", "DM&IP");
        details.put("email", "smth@smth.com");
        details.put("description", "Digital system for parking registration in big area");
        Map<String, Object> wrapper = new HashMap<>();
        wrapper.put("info", details);
        return new MapInfoContributor(wrapper);
    }
}
