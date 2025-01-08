package com.herbivore.demo.myapp.rest;

import com.herbivore.demo.myapp.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DemoController {
    // define a private field for dependency
    private Coach myCoach;
    private Coach myAnotherCoach;


    // define a constructor for dependency injection
    @Autowired
    public DemoController(@Qualifier("cricketCoach") Coach coach,
                          @Qualifier("cricketCoach") Coach anotherCoach) {
        System.out.println("[In constructor] " + getClass().getSimpleName());
        this.myCoach = coach;
        this.myAnotherCoach = anotherCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }

    @GetMapping("/check")
    public String check() {
        return "Comparing beans: myCoach == myAnotherCoach? (i.e. singleton/prototype?) "
               + (myCoach == myAnotherCoach)
               + ((myCoach == myAnotherCoach)? "(singleton)" : "(prototype)");
    }
}
