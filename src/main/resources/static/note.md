### Custom Security Table
 We can use custom tables/columns instead of `users` and `authorities` and their default columns 
#### Development Process:
1. Drop default, old tables `users` and `authorities` manually 
2. Run script 06 to create our custom replacements `members` and `roles`
3. Configure Java code to tell Spring to look up tables `members` and `roles` 
   * Otherwise, Spring will still use the default ones `users` and `authorities`