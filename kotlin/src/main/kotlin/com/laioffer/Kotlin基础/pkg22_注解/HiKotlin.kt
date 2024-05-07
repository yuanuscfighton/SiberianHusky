package Kotlin学习.pkg18_注解

// 注解（annotation）
// meta-annotation（元注解）

@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.EXPRESSION,
  AnnotationTarget.CONSTRUCTOR, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class MyAnnotation
