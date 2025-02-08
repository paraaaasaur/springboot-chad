# EXTRA: Spring Security With Unconventional ER In Database

_For cases where not only tables are not named "users" and "authorities", 
but also their entity relationship does not conform to what Spring Security 
expects by default.  
In such cases, we have to tweak things a little bit - entities & their 
relationship, configurations in our example - so that Spring Security can 
understand those unconventional tables._

* Your team has non-standard tables ER in database
  * Run script-02
* You configure entities and ER in Java code correctly with JPA
  * `User` and `Role` classes in this example
  * Also set up their DAOs
  * Specifically, set up `UserService` which extend `UserDetailsService`
* You need `UserService` for later use.
  * `UserService`:
    1. Does query on `user` table
    2. Does construct of `UserDetails` that should include
       1. username
       2. password
       3. **roles** (PLURAL! PLURAL! PLURAL!)
* In `@Configuration` class:
    ```java
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        return auth;
    }
    ```