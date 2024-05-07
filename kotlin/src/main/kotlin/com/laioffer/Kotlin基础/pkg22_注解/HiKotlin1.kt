package Kotlin学习.pkg18_注解

// 注解也可以拥有自己的构造方法，并且构造方法也可以接收参数
//注解构造方法所允许的参数类型：

//与Java原生类型所对应的类型（Int, Long..）
//字符串：string
//classes （MyClass::class）
//枚举：enums
//其他的注解
//上述类型的数组类型

//Kotlin的注解参数是不允许为可空类型的，因为JVM不支持以null的形式存储注解属性值的。

//如果某个注解被用作其他注解的参数，那么其名字就不需要以@字符开头

annotation class MyAnnotation1(val str: String)

@MyAnnotation1("hello")
class MyClass1

annotation class MyAnnotation2(val str: String, val myAnnotation: MyAnnotation1)
@MyAnnotation2("welcome", MyAnnotation1("hi"))
class MyClass2
