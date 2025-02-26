# Pointcut Declaration

## Reuse pointcut expression

1. Copy & Paste LUL
2. Use pointcut declaration

## Why Not Just Use a `public static final String`?

There are some other goods:
* Share and combine pointcut expressions

## Process

1. Create a pointcut declaration on top of a blank, labelling method
    ```java
    @Pointcut("execution(* com.herbivore..dao.*.*(..))")
	private void forDaoPackage() {}
    ```
2. Apply to the pointcut expression of an advice
    ```java
    @Before("forDaoPackage()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n>>>>> Executing @Before advice on addAccount()<<<<<");
    }
    ```