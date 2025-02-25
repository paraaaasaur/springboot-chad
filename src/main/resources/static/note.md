# Pointcut Expression Language

## Test Target

5. method parameter type

## Syntax for Execution Pointcuts (Applies to Execution of methods)

* `execution(modifiers-pattern? return-type-pattern declaring-type-pattern? method-name-pattern(param-pattern) throws-pattern?)`
* The pattern is optional if it has question mark(?)
* Basically, it's just method description without body
* Things to tweak for `public void addAccount()`
   * Wildcard (*):
      - add*(): any method prefixed with add
      - public * addAccount(): any return type
   * Fully Qualified Method name:
      - com.herbivore.demo.myapp.dao.*.addAccount()
   * Wildcard for method parameter:
      - (*) matches any single type
      - (..) matches any multiple types

## Questions