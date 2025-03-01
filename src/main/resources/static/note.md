# Advice Type

## @Around

### Basics

* ≈ `@Before`(before `proceed()`) + `@After`(after `proceed()`)
* ! Can handle exceptions: Swallow / Handle / Stop exceptions
* ! Has access to `ProceedingJoinPoint`
  - Requires `proceedingJoinPoint.proceed()` to execute the advised target method
  - `proceed()` returns the result object of proceeding 
* ! Unlike other type of advices, `@Around` has return statement
* ! Unlike `@AfterReturning`, you don't mutate result but return it like 
  normal Java code..........

### Common Uses
  * Logging, auditing, security
    * Pre-/Post-processing data
    * Instrumentation, profiling code
      - e.g. How long does it take for a section of code to finish?
    * Managing exception

### Caution

* Unlike other advice types, @Around doesn't run automatically 
  if you DON'T proceed() the target method in the advice.

### Question

1. Does return statement really work?
2. What do we do with the return object & thrown throwable?