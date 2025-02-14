# Custom Error Page For Access Denied Page

1. Add routing for exception handling > Access Deny to security filter chain
   * Feel free to name any routing name
      ```java
       @Bean
       public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
          // ...
      
           // exception handling
           http.exceptionHandling(configurer -> configurer
                   .accessDeniedPage("/access-denied-banana")
           );
      
           return http.build();
       }
      ```
2. Add routing method in controller
   * Spring doesn't give it for free this time
     ```java
      @GetMapping("/access-denied-banana")
      public String showAccessDeniedPasta() {
          return "access-denied";
      }
     ```
3. Add view (custom error page)