# @OneToOne: Bi-directional

* Convenience methods `associate` `dissociate` on referenced side to bind entities
  in a bidirectional relationship
  * Better clarity and code encapsulation rather than coding inline setters
  * No convenience method for dependent side (semantically wrong)

## Delete Both Parent and Child with `CascadeType.REMOVE`

1. em.find(parentId) => We get associated parent + child, thanks to bidirectional `@OneToOne`
2. em.remove(parentId) => Deleted parent + child, thanks to `CascadeType.REMOVE`

## REMOVE Scenarios For Strict Parent-Child Bi-1-1 Relationship
(Tested)  
1. `em.remove(parent);`
    - cascade remove on? ⇒ both deleted
    - cascade remove off? ⇒
        - orphan removal on? ⇒ both deleted
        - orphan removal off? ⇒ throw exception (persistence object references transient object)
2. `child.setParent(null);` cascade doesn’t matter here
    - orphan removal on? ⇒ child deleted
    - orphan removal off? ⇒ none
3. `parent.setChild(null);` updates child recordset FK to null.
    - Neither cascade nor orphanRemoval matters.

## Formulas For Deleting Child
(Tested)  
(`cascadeType = PERSIST` + no `orphanRemoval`)

- ⭐ dissociate() + em.remove(child) ⇒ child removed
- dissociate() ⇒ FK null (old truth)
- em.remove(child) ⇒ none
- `child.setParent(null)` + em.remove(child) ⇒ FK null!?
- `parent.setChild(null)` + em.remove(child) ⇒ child removed

======================================================================
## How do you know an entity's state? (Thank you da guru gpt✨)

### `EntityManager.contains(Object entity)`

This method checks if a given entity is associated with current persistence context

(i.e., in the **persistent** or **removed** state)

### **How to Check the State**

- **Check if an entity is managed (Persistent state):**

    ```java
    boolean isManaged = entityManager.contains(myEntity);
    ```

    - If `true`, the entity is in the **Persistent** state.
    - If `false`, the entity is either **Detached** or **Transient**.
- **Check if an entity is Detached:**
  If `entityManager.contains(myEntity)` returns `false` but the entity has a valid ID (exists in DB), it’s likely **Detached**.
- **Check if an entity is Transient:**
  If the entity has `null` or default ID (not saved yet), it’s likely **Transient**.
- **Check if an entity is Removed:**
  If `entityManager.contains(myEntity)` is `true` but `entityManager.remove(myEntity)` has been called, it's in the **Removed** state.