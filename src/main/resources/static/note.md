# @OneToMany Bidirectional

## Case: 1=instructor, M=course

* No cascade-remove, i.e. 
  * deleting instructor doesn't delete course
  * deleting course doesn't delete instructor
  * (but there might be 1-M cases where cascade-remove makes sense right?)

## Development Process

1. DB setup
   * ch08-my-version.sql, part 3
2. Update `spring.datasource.url=jdbc:mysql://localhost:3306/hb-03-one-to-many`
3. Create `Course` class
   * @ManyToOne @JoinColumn
   * Convenience method
     * Cascade: Me > NONE; Chad > ALL except REMOVE
   * [optional] override `hashcode` `equals` if using `Set` as collection
4. Update `Instructor` class
   * @OneToMany
     * Cascade: ALL except REMOVE
   * Convenience method
    ```java
    public void associate(Course... courses) {
        for (var course : courses) {
            this.courses.add(course);
            course.setInstructor(this);
        }
    }
    
    public void dissociateCourses(Course... courses) {
        Arrays.stream(courses)
                .filter(Objects::nonNull)
                .forEach(course -> {
                    course.setInstructor(null);
                    this.courses.remove(course);
                });
    }
    
    public void dissociateAllCourses() {
        courses.remove(null);
        courses.forEach(course -> {
            course.setInstructor(null);
            courses.remove(course);
        });
    }
    ``` 
5. Create main app

* [@OneToMany](https://docs.jboss.org/hibernate/orm/6.6/userguide/html_single/Hibernate_User_Guide.html#associations-one-to-many):  
  The `@OneToMany` association links a parent entity with one or more 
  child entities. If the `@OneToMany` doesn’t have a mirroring @ManyToOne 
  association on the child side, the `@OneToMany` association is unidirectional. 
  If there is a `@ManyToOne` association on the child side, the `@OneToMany` 
  association is bidirectional and the application developer can navigate this 
  relationship from both ends.