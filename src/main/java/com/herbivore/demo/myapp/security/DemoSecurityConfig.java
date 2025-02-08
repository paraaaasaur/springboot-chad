package com.herbivore.demo.myapp.security;

import com.herbivore.demo.myapp.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

	// (commented it because it malfunctions 🥺)
	// bcrypt bean definition
	// @Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// authenticationProvider bean definition
	@Bean
	public DaoAuthenticationProvider authenticationProvider(UserService userService) {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		// The reason we need UserService (which extends UserDetailsService)
		auth.setUserDetailsService(userService);
		// Set the password encoder as BCrypt
		// (Not working as expected, so I commented it out)
//		auth.setPasswordEncoder(passwordEncoder());
		return auth;
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

	private class Archived {
		// @Bean
		@Deprecated(since = "Hardcoding era is over :-)")
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
}

