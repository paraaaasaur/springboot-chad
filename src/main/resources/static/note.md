# @OneToOne: Uni-directional

## Development Process

1. Define database tables
   * ch08-create-db.sql
   * 
2. Create corresponding entities `InstructorDetail` and `Instructor`
   * Prepare POJO + must include no-arg constructor
   * `@Entity` `@Table` `@Column`
   * `@JoinColumn` `@OneToOne`
   * `@Repository` `@Transactional`
3. Create main app using `CommandLineRunner`

## More On The Purpose Foreign Key

* To preserve relationship between tables 
  * AKA Referential Integrity
* To prevent operations that would destroy relationship
* Ensures only valid data can be inserted into the FK column
  * Can only contain valid reference to PK in other tables

## Entity Lifecycle

* States: New/Transient, Persistent/Managed, Detached, Removed
* Common Actions: Detach, merge, persist, remove, refresh...

## Cascade Type

* When the entity is <action>, the related entity also get <action>
* By default, there is no cascade type
* PERSIST, REMOVE, REFRESH, DETACH, MERGE, ALL

## Notice

* Because of cascading setting, inserting `instructor` also inserts `instructor_detail` 
* `instructor_detail` table gets inserted first
* `@Transactional` should have put on `@Service` layer
