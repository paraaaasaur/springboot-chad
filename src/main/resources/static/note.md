### Extra notes that doesn't fit in code lines go here

Development Process:

* Modify DB column `password` in table `users` to `char 68` with script 05
  * So that it exactly fits the encoding ID `{bcrypt}`(8) + the hash(60)
  * FYI: BCrypt structure = `$[version]$[cost]$[salt][hash]`