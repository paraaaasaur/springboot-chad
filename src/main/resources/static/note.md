### Extra notes that doesn't fit in code lines go here

Development Process:

* Set up SQL tables `users` and `authorities`(AKA`roles`) using script 04
  * These 2 Tables and their column names are default to Spring  
  * So is their relation   
* Modify `UserDetails` generation method to read-credentials-from-db version 