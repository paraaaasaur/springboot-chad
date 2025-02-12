# Refactoring With BS + Root-Page Redirection

## Bootstrap

* Add dependency CSS & JS via CDNs
* Classes
  * table: `table table-border table-striped`
  * thead: `table-dark`
  * tbody: `table-warning`
  * tfoot: `table-info`

## Redirection

* _index.html_ is the convention fallback at root mapping `("/")` recognized by most web servers
  * You can set up a routing method instead if you wish
  * _index.html_ goes in _/static_, because it's not a dynamic page which needs processing by the server first, like Thymeleaf templates.
* A single line for redirection: `<meta http-equiv="refresh" content="0; URL='employees/list'">`