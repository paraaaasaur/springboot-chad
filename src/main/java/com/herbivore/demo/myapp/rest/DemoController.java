package com.herbivore.demo.myapp.rest;

import com.herbivore.demo.myapp.common.Coach;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DemoController {
    // define a private field for dependency
    private Coach myCoach;

    // define a constructor for dependency injection
    @Autowired
    public DemoController(@Qualifier("shubaDuck") Coach coach) {
        System.out.println("[In constructor] " + getClass().getSimpleName());
        this.myCoach = coach;
    }

    @PostConstruct
    public void aparecium() {
        System.out.println("✨ Aparecium! MagicalBean is ready for action.");
    }

    @PreDestroy
    public void evanesco() {
        System.out.println("✨ Evanesco! MagicalBean is vanishing gracefully.");
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }
}
