package com.laioffer.lesson.手写dagger.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类的描述: 标记: e.g. 提供Database的对象，i.e. 将Database打包成包裹
 * Created by 春夏秋冬在中南 on 2023/8/27 15:09
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Provides {
}
