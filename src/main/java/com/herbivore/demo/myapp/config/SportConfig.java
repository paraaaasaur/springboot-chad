package com.herbivore.demo.myapp.config;

import com.herbivore.demo.myapp.common.Coach;
import com.herbivore.demo.myapp.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    @Bean("shubaDuck") // bean name override
    public Coach swimCoach() {
        return new SwimCoach();
    }
}
