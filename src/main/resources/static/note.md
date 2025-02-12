# [C]rud: Add Employee

## 1. Scheme
* list-employees.html: onclick 
* --(GET /employees/add)--> 
* add-employee-form.html: onsubmit
* --(POST /employees/add ... INSERT logic ... redirect)-->
* list-employees.html

## 2. Bootstrap Styling
* Button style (‼️ works for everything: button, a, input): 
  * `btn btn-success btn-md mb-3`
  * `btn btn-info col-2`
* Form input: `form-control mb-4 w-25`

## 3. Spring Data JPA Custom Method (to Sort By lastName)
* Create a method on the repository & follow the IDE naming hint, and then it shall implement a query based on that naming
* `List<Employee> findAllByOrderByLastNameAsc();`