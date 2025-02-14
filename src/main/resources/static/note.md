# JDBC Authentication (Plain-Text, NOOP)

## Development Process

1. Add Maven Dependency
2. Configure SQL Tables
   * `users`/`authorities` by default
   * Script 04
3. Configure JDBC support in _application.properties_
   ```properties
   # JDBC Properties
   spring.datasource.url=jdbc:mysql://localhost:3306/employee_directory
   spring.datasource.username=springstudent
   spring.datasource.password=springstudent
   
   # DISCLAIMER: This will log username, so use it only for dev/testing
   # and DO NOT USE IT IN PRODUCTION ENVIRONMENT
   logging.level.org.springframework.jdbc.core=TRACE
   ```
   Extra TRACE level information:
   ```text
   JdbcTemplate         : Executing prepared SQL query
   JdbcTemplate         : Executing prepared SQL statement [select username,password,enabled from users where username = ?]
   StatementCreatorUtils: Setting SQL statement parameter value: column index 1, parameter value [akutan], value class [java.lang.String], SQL type unknown
   ```
4. Configure JDBC support in @Configuration class
   ```java
   // add support for instead of hardcoding credentials
   @Bean
   public UserDetailsManager userDetailsManager(DataSource dataSource) {
       // tell Spring Security to JDBC authentication with our database
      return new JdbcUserDetailsManager(dataSource);
   }
   ```