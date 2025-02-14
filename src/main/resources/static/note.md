# JDBC Authentication (BCrypt, {bcrypt})

## Development Process

* Configure SQL
   * DDL: ALTER TABLE so that `users.password` row is exactly 68 chars
   * DML: UPDATE `users.password` table to be {bcrypt}-encoded
   * (Script 05)

* [BCrypt Calculator](https://www.bcryptcalculator.com/encode)