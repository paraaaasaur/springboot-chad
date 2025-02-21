# EXTRA: PersistenceUtil

* Methods to test if the object/collection is lazy/not loaded:
  ```java
  // General way using JPA
  persistenceUtil.isLoaded(lazyCourses);
  persistenceUtil.isLoaded(foundInstructor, "courses");
  // Hibernate-specific
  Hibernate.isInitialized(lazyCourses);
  ```

## Default `FetchType`

* `@OneToOne`: EAGER (Also, LAZY has no effect on parent side)
* `@OneToMany`: LAZY
* `@ManyToOne`: EAGER
* `@ManyToMany`: LAZY