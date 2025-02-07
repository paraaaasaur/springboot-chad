package com.herbivore.demo.myapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
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

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(configurer -> {
			// Restricting access based on roles 
			configurer
					.requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
					.requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
					.requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
					.requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
					.requestMatchers(HttpMethod.DELETE, "/api/employees").hasRole("ADMIN");
		});
		// Auth type: use 'Basic Auth'
		http.httpBasic(Customizer.withDefaults());

		// CSRF: off, since it's redundant for a pure REST API
		http.csrf(csrf -> csrf.disable());

		return http.build();
	}
}
