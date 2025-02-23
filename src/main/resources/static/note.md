# M-M Association (2-Sided Unidirectional) - Setup

## Preparation

1. Define database tables (`hb-05-many-to-many`)
2. `spring.datasource.url=jdbc:mysql://localhost:3306/hb-05-many-to-many`
3. Create `Student` entity
4. Associate `Student` and `Course` entities
   * `@ManyToMany` on association fields

## Basics

* Requires a **join/junction/link/intermediate table** to work.  
  Its job is to keep track of all associations between the two entities.
* Do we have parent/child or owning/inverse sides?
  * In database: No, they are meaningless in M-M
  * In JPA: Yes, but it's more for technicality rather than real meaning
* Implementation: 
  1. uni M-M 
  2. bi M-M 
  3. 2-sided-uni M-M
  4. 2-sided-uni 1-M

## Our Case: Course <--> Student

* Implementation: 2-sided-unidirectional M-M
* No convenience method (since it's effectively unidirectional from JPA's view)
* Cascade Type: No REMOVE (it rarely make senses in normal M-M)
* Fetch Type: LAZY

## @ManyToMany

* Used in owning side... but in our case both sides are owning sides
* **Inverse** refers to the other side
* Property `@JoinTable`
  - name: Specifies the name of join table to use
  - joinColumns: "This side" column in the join table
  - inverseJoinColumns: "Inverse side" column in the join table

## Qs

* Forget about ORM and just speak strictly from database's perspective, 
  is there such a thing as parent/child or owning/inverse relationship 
  in M-M association?
* Why do you go against Javadoc? -> Well code first I guess...