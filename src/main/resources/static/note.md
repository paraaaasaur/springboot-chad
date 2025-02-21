# [Fetching Association](https://docs.jboss.org/hibernate/orm/6.6/userguide/html_single/Hibernate_User_Guide.html#best-practices-fetching-associations)

## Lazy Loading

* Load data only **when absolutely needed**
* Or: Load the main entity first, and the dependent entities on demand 
* Imagine a course has 50,000 students, and a query on course brings back all the records
* Lazy Objects/Collections
  - Lazy objects are useless for retrieving all fields, except for PK
  - Lazy collections are completely useless to open, always throws `LazyInitializationException`
  - Replace them using newly fetched data using `setters`!
    - Convenience methods are not for lazy data...

## Test

* @OneToOne: 
  * child LAZY: select id1_0.id,id1_0.hobby,id1_0.instructor_id,id1_0.youtube_channel from instructor_detail id1_0 where id1_0.id=?
  * child EAGER(default): select id1_0.id,id1_0.hobby,i1_0.id,i1_0.email,i1_0.first_name,i1_0.last_name,id1_0.youtube_channel from instructor_detail id1_0 left join instructor i1_0 on i1_0.id=id1_0.instructor_id where id1_0.id=?

## `LazyInitializationException`

* Reading: [Hibernate could not initialize proxy – no Session](https://www.baeldung.com/hibernate-initialize-proxy-exception)
* Cause: Fail to lazily initialize after the session closed
* Solution:
  1. Fetch eagerly
  2. Retrieve separately & associate them
* Methods to test if the object/collection is lazy/not loaded:
  ```java
  persistenceUtil.isLoaded(lazyCourses);
  persistenceUtil.isLoaded(foundInstructor, "courses");
  Hibernate.isInitialized(lazyCourses);
  ```

## Default `FetchType`

* `@OneToOne`: EAGER (Also, LAZY has no effect on parent side)
* `@OneToMany`: LAZY
* `@ManyToOne`: EAGER
* `@ManyToMany`: LAZY