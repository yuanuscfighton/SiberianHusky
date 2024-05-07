package Kotlin学习.pkg18_注解

import kotlin.reflect.KClass


// 如果需要将某个class作为注解的参数，那么请使用Kotlin class（KClass）
//    Kotlin编译器会自动将其转换为Java Class
//    这样，Java代码就可以正常看到注解与参数了。

annotation class MyAnnotation3(val arg1: KClass<*>, val arg2: KClass<out Any>)

@MyAnnotation3(String::class, Int::class)
class MyClass3
