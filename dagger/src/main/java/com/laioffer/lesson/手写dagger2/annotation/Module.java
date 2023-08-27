package com.laioffer.lesson.手写dagger2.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description 标记: e.g. 提供Database的对象，i.e. 将Database打包成包裹
 * @date 2022/11/29 9:14 PM
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Module {
}
