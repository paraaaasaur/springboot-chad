# Display Content Based On Roles

* Thymeleaf/Spring-Security dialect: render contents based on `sec:authorize` condition
   ```thymeleafexpressions
    <p sec:authorize="hasRole('MANAGER')">
        <a th:href="@{/leaders}">Leadership Meeting</a>
        (Requires MANAGER role)
    </p>
   ```