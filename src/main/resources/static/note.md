# Joint Point

* Definition: When to apply the code during program execution

## JoinPoint Instance

* Gives metadata about the method call
* Access method signature, args...

## Common Uses

1. Display Method Signature: `(MethodSignature) jointPoint.getSignature();`
   * (Downcast is safe according to Javadocs on `Signature.class`)
2. Args Iteration: `jointPoint.getArgs();`