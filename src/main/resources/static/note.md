# CH9 Advanced JPA(ORM) Mapping: Setup

## Foundational Concepts

* JPA(ORM) Relationships Concepts
  1. one-to-one(instructors <-- instructor_details)
  2. one-to-many(carts <-- items)
  3. many-to-many(students <--> courses)
* PK, FK
* Cascading: Apply the same operation to related entities
  * Cascade Delete: 
    * ✅ Intuitive on `one-to-one` or `one-to-many`
    * ⚠️ Caution with `many-to-many`:
      * Problem: Deleting a student shouldn't delete their courses!
      * Solution: First define your goal, then express it in SQL.
       > Example: "I want to delete a course only if no students are enrolled."

* Fetch Types: Want to retrieve all related data from related tables in one shot? 
    * EAGER: Yes
    * LAZY: Nope until requested otherwise
* Uni-/Bi-directional Relationship
  * Uni: `Instructor` has a reference to `InstructorDetail`, but not vice versa 
  * Bi: Both `Instructor` and `InstructorDetail` reference each other directly, allowing mutual access.

# `@OneToOne` Relationship