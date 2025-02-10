# Checkbox In Thymeleaf

* Bug: Overriding `toString` will cause conversion error when using enum in `th:value`
    * Even if it looks like you are using the enum const, it mistakenly calls `toString` anyway if overwritten
