package Kotlin学习.pkg18_注解

// 在对类的属性或是主构造方法的参数声明注解时，会存在多个Java元素都可以通过对应的Kotlin元素生成出来，
//    因此，在所生成的Java字节码中，就会存在多个可能的位置来生成相应的注解。若想精确指定如何来生成注解，那么可以
//    使用注解的使用处目标方式来实现。
class MyClass7(@field: MyAnnotation val arg1: String,  // 注解 Java field
               @get: MyAnnotation val arg2: String,    // 注解 Java getter
               @param: MyAnnotation val arg3: String)  // 注解 Java构造方法参数
