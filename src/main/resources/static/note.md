# @OneToOne: Uni-directional, UPDATE + DELETE







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