# Field Validation

> ‼️ We are using annotations under the jakarta package, not jetbrains one.

1. Add jakarta annotations on fields, e.g. `@NotNull` `@Size` `@Pattern`

  ```java
  public class Customer {
  
      private String firstName;
  
      @NotNull(message = "is required")
      @Size(min = 1, message = "is required!")
      @Pattern(regexp = "\\p{Alpha}+", message = "alphabetical!")
      private String lastName;
      
      // ...
  }
  ```

2. Add error conditions on the view

  ```html
  Firstname: <input type="text" th:field="*{firstName}" />
  <br>
  Lastname(*): <input type="text" th:field="*{lastName}" />
  
  **<!-- Add error message, if present -->
  <span th:if="${#fields.hasErrors('lastName')}"
        th:errors="*{lastName}"
        class="errorStyle"></span>**
  ```

3. to-controller: `bindingResult.hasErrors()`

  ```java
  @PostMapping("/processForm")
  public String processForm(
          @Valid @ModelAttribute("customer") Customer customer,
          BindingResult bindingResult
  ) {
      return bindingResult.hasErrors()?
              "customer-form" :
              "customer-confirmation";
  }
  ```