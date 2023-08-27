package com.laioffer.lesson.手写dagger2.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description 标记: 快递员角色，e.g. ObjComponents
 * @date 2022/11/29 9:00 PM
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {

  Class<?>[] modules() default {};
}
