# Ordering Aspects (and Advices)

Within the same aspect, there is no guarantee which advice will be run first:
  ```java
  @Before("execution(somewhere)")
  public void advice01() {/* advice01 logic*/}
  
  @Before("execution(somewhere)")
  public void advice02() {/* advice02 logic*/}
  ```

## Controlling Order

1. Refactor: Place advices in separate advices
2. Control order on aspects using `@Order` annotation
3. [Optional] Move pointcut declarations to a separate class for my eyes

## `@Order`

* Only works for Spring Beans
* Examples:
  - Aspects
  ```java
  @Order(0x0001) @Component @Aspect public class Aspect01 {/* First advices*/}
  @Order(0b0010) @Component @Aspect public class Aspect02 {/* Later advices*/} 
  ```
  - `CommandLineRunner` bean
  ```java
  @Order(1) @Bean public CommandLineRunner clr01() {return args -> {/* Runs first*/};}
  @Order(2) @Bean public CommandLineRunner clr02() {return args -> {/* Runs next*/};} 
  ```
  
## Extra

* Switch logging to DEBUG mode to examine AOP syndrome if needed