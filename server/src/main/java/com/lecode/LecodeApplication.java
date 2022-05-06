package com.lecode;

import com.lecode.view.LecodeView;
import java.net.UnknownHostException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class LecodeApplication {

    public static void main(String[] args) throws UnknownHostException {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(LecodeApplication.class);
        builder.headless(false);
        builder.run(args);
        LecodeView view = new LecodeView();
        view.setVisible(true);
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("/*")
                        .allowedMethods("POST", "GET", "PUT", "DELETE", "HEAD", "PATCH", "OPTIONS");

            }
        };
    }

}
