# cr[U]d: Editing Employee

## 1. [Standard Thymeleaf URL Syntax](https://www.thymeleaf.org/doc/articles/standardurlsyntax.html)

### Include Path Variable
* `@{/employees/edit/{id}(id=${emp.id})}` generates `/employees/edit/43`

### Include Request Parameter (Query String)
* `@{/employees/edit(id=${emp.id})}` generates `/employees/edit?id=43`

## 2. Param Order Pitfall
Lesson: BindingResult should go right after @Valid object, because Spring uses 
Reflection to do the work UTH

### Wrong param order 
When validation detects constraint violations:
* Doesn't display nice error message
* Doesn't even execute the method
* Only displays error info on the console
```java
@PostMapping("/edit/{id}")
public String editEmployee(
        @Valid @ModelAttribute("employee") Employee employee,
        String uselessRoadBlockLUL, // misplaced!
        BindingResult bindingResult
) {
    // Not even executing method when there is constraint violation
    // Works normally otherwise though 
}
```
### Correct param order
```java
@PostMapping("/edit/{id}")
public String editEmployee(
        String uselessRoadBlockLUL,
        @Valid @ModelAttribute("employee") Employee employee,
        BindingResult bindingResult
) {
    // Functions as expected
}
```
