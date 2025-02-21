# Delete Instructor Alone

* Purpose: DELETE an instructor after dissociating with child records
* Process:
  1. Update `deleteInstructorById` method to ensure dissociation before remove
  2. Run it
* Dissociation doesn't trigger `LazyInitializationException` within a transaction(session)