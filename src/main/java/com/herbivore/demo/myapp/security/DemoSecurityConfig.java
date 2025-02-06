package com.herbivore.demo.myapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class DemoSecurityConfig {

	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails akutan = User.builder()
				.username("akutan")
				// encoding prefix is required
				.password("{noop}athc")
				.roles("EMPLOYEE")
				.build();

		UserDetails takuan = User.builder()
				.username("takuan")
				// encoding prefix is required
				.password("{noop}majidesaa")
				.roles("EMPLOYEE", "MANAGER")
				.build();

		UserDetails toyota = User.builder()
				.username("toyota")
				// encoding prefix is required
				.password("{noop}!?!?!?")
				.roles("EMPLOYEE", "MANAGER", "ADMIN")
				.build();

		return new InMemoryUserDetailsManager(akutan, takuan, toyota);
	}
}
