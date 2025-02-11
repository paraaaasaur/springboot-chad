# Validation By Custom Annotation

## Requirement

1. Custom annotation class
2. Helper validation class for the annotation.
    * It should implement `ConstraintValidator`

## Grouping

1. Prepare blank interfaces for grouping purpose. Give meaningful class names btw.
2. `@GroupingSequence({G1.class, G2.class, Customer.class})` above the model class
   * Now group G1 gets validated first. If passed, then G2.
   * Model class has to be included at the end
3. Now group your constraint annotations by using `group` parameter to refer to.
    ```java
        @NotNull(groups = {MajorGroup.class})
        @CourseCode(groups = {MinorGroup.class})
        private String courseCode;
    ```