# Advice Type

* Covered @Before (same for @After)

## @AfterReturning Advice

* Comes in after a method ends without throwing anything (i.e. works for void methods as well)
* Has access to return values
  * Display, modify...
* WARNING: The final returned value in the outer method is this tweaked one.

### Properties

* `pointcut = execution(...)` same meaning as before
* `returning = "rawResult` 
  * Specifies and links to the param name `rawResult` that stands for  
    the return value of the advised target method.

## Process

1. Add constructors to Account.class
2. Add `selectAccounts()` method in DAO to return a List<Account>
3. Add a service method `demoTheAfterReturnAdvice()` to call DAO method `selectAccounts()`
4. Add `@AfterReturning` advice

## Thought

1. Immutable copy of return value in case of modifying

## Question

1. Difference between `@After` and `@AfterReturning` for success case