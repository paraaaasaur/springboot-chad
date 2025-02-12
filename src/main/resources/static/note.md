# cru[D]: Deleting Employee

* DELETE request in Spring Boot requires: 
  * Payload contains `_method=DELETE`
  * _application.properties_: `spring.mvc.hiddenmethod.filter.enabled=true`
  * `@DeleteMapping` method