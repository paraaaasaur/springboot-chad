# Custom Login Form

1. Modify @Configuration so that Spring references to custom logic form
   ```java
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
   ```
2. Prepare custom login endpoint
3. Prepare custom login view
   * Requirement: include username and password in payload 