package com.herbivore.demo.myapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {

		UserDetails akutan = User.builder()
				.username("akutan")
				.password("{noop}athc")
				.roles("EMPLOYEE")
				.build();

		UserDetails takuan = User.builder()
				.username("takuan")
				.password("{noop}majidesaa")
				.roles("EMPLOYEE", "MANAGER")
				.build();

		UserDetails toyota = User.builder()
				.username("toyota")
				.password("{noop}!?!?!?")
				.roles("EMPLOYEE", "MANAGER", "ADMIN")
				.build();

		return new InMemoryUserDetailsManager(akutan, takuan, toyota);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(configurer ->
			configurer.anyRequest().authenticated()
		);

		http.formLogin(form -> form
				// GET /showMyLoginPage
				.loginPage("/showMyLoginPage")
				// POST /authenticateTheUser: No need to implement
				// manually - Spring Boot offers default implementation
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll());

		return http.build();
	}
}
