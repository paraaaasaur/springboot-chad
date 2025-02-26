# Pointcut Declaration: Combining Pointcuts

## Available Logic Operators

* AND(&&), OR(||), NOT(!)
    ```java
  @Component
  @Aspect
  public class MyDemoLoggingAspect {
  
        @Pointcut("execution(* com.herbivore..dao.*.*(..))")
        private void forDaoPackage() {}
    
        @Pointcut("execution(* com.herbivore..dao.*.find*(..))")
        private void find() {}
    
        @Pointcut("execution(* com.herbivore..dao.*.update*(..))")
        private void update() {}
    
        @Pointcut("forDaoPackage() && !(find() || update())")
        private void forDaoPackageNoFindUpdate() {}

        @Before("forDaoPackageNoFindUpdate()")
        public void beforeAddAccountAdvice() {
            // advice detail
        }
        
        @After("forDaoPackageNoFindUpdate()")
        public void performFabulousApiAnalytics() {
            // advice detail
        }
  }
    ```

## Process

1. Add some test target objects(methods) to DAOs
2. Create more pointcut declarations
3. Combine them
4. Apply them to advices

## Question

1. Can you combine raw pointcutExp Strings too?