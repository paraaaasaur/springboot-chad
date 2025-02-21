# `JOIN FETCH` Query In Lazy Fetching

* Basically, LAZY fetching is still performant strategy, but use with caution 
* `JOIN FETCH` forces the retrieval of lazily loaded data in a single query 
* SQL used in JOIN FETCH:
* For `SELECT i FROM Instructor i JOIN FETCH i.courses WHERE i.id = :id`:
  * Requires 2 queries in total
  ```sql
  SELECT * FROM instructor i 
  JOIN course c ON i.id = c.instructor_id 
  WHERE i.id = ?
  ```
  ```sql
  SELECT * FROM instructor_detail id 
  LEFT JOIN instructor i ON i.id = id.instructor_id 
  WHERE id.instructor_id = ?
  ``` 
* For `SELECT i FROM Instructor i JOIN FETCH i.courses 
       JOIN FETCH i.instructorDetail WHERE i.id = :id`:
  * Requires only 1 query
  ```sql
  SELECT * FROM instructor i 
  JOIN course c ON i.id = c.instructor_id 
  JOIN instructor_detail id ON i.id = id.instructor_id 
  WHERE i.id = ?
  ``` 
* If we only need instructor: `findInstructorById`
* If we only need instructor + courses: `findInstructorByIdJoinFetch`

### JOIN FETCH: Inner Join(Default)
```java
String jpql = "SELECT i FROM Instructor i " +
              "JOIN FETCH i.courses " +
              "JOIN FETCH i.instructorDetail " +
              "WHERE i.id = :id";
```
- Performs an inner join 
- Requires matching records in all tables
- `NoResultException` even if one of the children doesn't match 

### LEFT JOIN FETCH: Left Join
```java
String jpql = "SELECT i FROM Instructor i " +
              "LEFT JOIN FETCH i.courses " +
              "LEFT JOIN FETCH i.instructorDetail " +
              "WHERE i.id = :id";
```
- Allows no match (NULL) in child tables
- SQL Equivalent: 
```sql
SELECT * FROM instructor i
JOIN instructor_detail d ON i.id = d.instructor_id
JOIN course c ON i.id = c.instructor_id
WHERE i.id = ?;
```

## Extra
- Hand-made DTO Projection
- Tuple -> bad because it's for flat fields only