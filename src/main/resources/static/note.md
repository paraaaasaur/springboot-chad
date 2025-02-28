# Advice Type

## @AfterReturning Advice: Post-Processing Return Value

* ‼️ The processed object is referenced directly, not through the variable 
  pointing to it. Direct mutations are required for changes to take effect.
* 
    ```java
        @AfterReturning(pointcut = "selectAccounts()", returning = "rawResult")
        public void afterReturningSelectAccountsAdvice(
                JoinPoint joinPoint,
                List<Account> rawResult
        ) {
            // Post-Process return value
            // Direct mutation -> has effects
            rawResult.forEach(account -> {
                account.setName("foo");
                account.setLevel("bar");  
            });
  
            // Assigning a new reference -> no effect
            rawResult = processedResult; 
            rawResult = null;
  
        }
    ```