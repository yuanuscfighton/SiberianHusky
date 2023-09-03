package com.laioffer.lesson.手写dagger.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 类的描述: 标记: 快递员角色，e.g. ObjComponents
 * Created by 春夏秋冬在中南 on 2023/8/27 15:08
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {

  Class<?>[] modules() default {};
}
