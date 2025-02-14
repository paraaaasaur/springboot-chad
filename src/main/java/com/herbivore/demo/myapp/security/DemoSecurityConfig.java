package com.herbivore.demo.myapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

	// add support for instead of hardcoding credentials
	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {

		// tell Spring Security to JDBC authentication with our database
		var judm = new JdbcUserDetailsManager(dataSource);
		String[] queries = {
				"SELECT user_id,pw,active FROM members WHERE user_id=?",
				"SELECT user_id,role FROM roles WHERE user_id=?"
		};

		judm.setUsersByUsernameQuery(queries[0]);
		judm.setAuthoritiesByUsernameQuery(queries[1]);

		return judm;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(configurer ->
			configurer
					.requestMatchers("/").hasRole("EMPLOYEE")
					.requestMatchers("/leaders/**").hasRole("MANAGER")
					.requestMatchers("/systems/**").hasRole("ADMIN")
					.anyRequest().authenticated()
		);

		http.formLogin(form -> form
				// GET /showMyLoginPage
				.loginPage("/showMyLoginPage")
				// POST /authenticateTheUser: No need to implement
				// manually - Spring Boot offers default implementation
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll());

		// logout support
		http.logout(LogoutConfigurer::permitAll);

		// exception handling
		http.exceptionHandling(configurer -> configurer
				.accessDeniedPage("/access-denied-banana")
		);

		return http.build();
	}


	private class Archived {
		// @Bean
		@Deprecated(since = "Hardcoding era is over :-)")
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
	}
}
