# Logout

1. Modify Spring Security config to add logout support
2. Add logout button
3. Add logout message

* `POST /logout` endpoint is implemented by Spring Boot
* When you log out, Spring
  1. Invalidates your HTTP session
  2. Removes your session cookies 
  3. Redirect user back to login page + `param.logout`