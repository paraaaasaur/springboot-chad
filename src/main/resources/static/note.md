# JDBC Authentication With Custom Table Schema

* Default table schema in Spring Security: `users` + `authorities`
* Let's say we want to use our custom tables`members` + `roles` instead

## Development Process

0. Prepare your custom table schema for Spring security
   * Script-06
   * [Optional] DROP TABLE `users` and `authorities`
1. `@Configuration` class: Configure query for JDBC auth 
so that Spring can find your table
   ```java
   @Bean
   public UserDetailsManager userDetailsManager(DataSource dataSource) {
       var judm = new JdbcUserDetailsManager(dataSource);
       String[] queries = {
               "SELECT user_id,pw,active FROM members WHERE user_id=?",
               "SELECT user_id,role FROM roles WHERE user_id=?"
       };
       judm.setUsersByUsernameQuery(queries[0]);
       judm.setAuthoritiesByUsernameQuery(queries[1]);
       return judm;
   }
   ```  