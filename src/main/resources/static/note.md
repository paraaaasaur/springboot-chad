# Restrict Access Based On Roles

* key: 
```java
http.authorizeHttpRequests(configurer ->
    configurer
            .requestMatchers("/").hasRole("EMPLOYEE")
            .requestMatchers("/leaders/**").hasRole("MANAGER")
            .requestMatchers("/systems/**").hasRole("ADMIN")
            .anyRequest().authenticated()
);
```