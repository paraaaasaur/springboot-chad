# DELETE

* When we talk about delete in M-M relationship, think twice about what you want to do:
  1. Delete a course entirely (and also the associated links)?
  2. Delete a student entirely (and also the associated links)?
  3. Delete the association only (without deleting any student/course record)?
     - AKA remove a student/course from a course/student

## 1. Delete a Course

* Java: `em.remove(dbCourse);`
* Effect: First deleted the association(s), and then deleted the course
* No need to remove association manually (by setting null); JPA handles it in M-M
* Console SQL
    ```sql
    DELETE FROM course_student WHERE course_id=?
    DELETE FROM course WHERE id=?
    ```

## 2. Delete a Student

* Java: `em.remove(dbStudent);`
* Effect: First deleted the association(s), and then deleted the student
* No need to remove association manually (by setting null); JPA handles it in M-M
* Console SQL
    ```sql
    DELETE FROM course_student WHERE student_id=?
    DELETE FROM student WHERE id=?
    ```

## 3. Delete an Association (To Be Continued)