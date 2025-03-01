package com.herbivore.demo.myapp.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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
}
