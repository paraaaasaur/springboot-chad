package com.herbivore.demo.myapp.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static io.github.paraaaasaur.util.Toolbox.yellow;

@Service
public class TrafficFortuneServiceImpl implements TrafficFortuneService {

	@Override
	public String getFortune() {
		// Simulate a delay
		try {
			for (int i = 0; i < 3; i++) {
				System.out.println((3 - i) + "...");
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e.getMessage());
		}

		// Return a fortune String
		return "No traffic jam after 5pm";
	}

//	@Transactional
	@Override
	public String getFortune(boolean tripWire) {
		boolean oneThrid = ThreadLocalRandom.current().nextDouble() < 0.3333333;
		if (tripWire) {
			if (oneThrid) {
				throw new UnsupportedOperationException(yellow("A gaur is passing by please wait"));
			} else {
				throw new StackOverflowError(yellow("Sky is raining gaurs please slow down your speed"));
			}
		}

		return getFortune();
	}
}
