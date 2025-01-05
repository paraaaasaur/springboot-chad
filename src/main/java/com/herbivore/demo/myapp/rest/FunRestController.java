package com.herbivore.demo.myapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class FunRestController {


    @GetMapping("/") // expose "/" that returns "hello world"
    public String sayHello() {
        return "<h1 style='font-size: 3em'>Hello World!?!?!?</h1>";
    }

    @GetMapping("/workout")
    public String getDailyWorkout() {
        return "<h1>Run a hard 5k!?</h1>";
    }

    @GetMapping("/fortune")
    public String getDailyFortune() {
        final int rand = new Random().ints(0, 8).limit(1).sum();

        return switch (rand) {
            case 0 -> "daikichi (大吉): Great fortune! 🌟 Everything is aligned in your favor.";
            case 1 -> "chukichi (中吉): Moderate fortune! 😊 Things are looking pretty good.";
            case 2 -> "shokichi (小吉): Small fortune! 🌱 Small blessings, but they still count.";
            case 3 -> "kichi (吉): Fortune! 🌞 Steady progress awaits you.";
            case 4 -> "suekichi (末吉): Future fortune! 🌈 Patience will bring good things.";
            case 5 -> "kyou (凶): Bad fortune! 🌧 Be cautious and stay positive.";
            case 6 -> "daikyou (大凶): Great misfortune! ⚡ Challenges are ahead—stay strong.";
            default -> "Unknown fortune! 🌌 Embrace the mystery.";
        };
    }

}
