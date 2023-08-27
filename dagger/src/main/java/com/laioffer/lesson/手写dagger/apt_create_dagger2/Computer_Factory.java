package com.laioffer.lesson.手写dagger.apt_create_dagger2;


import com.laioffer.lesson.手写dagger.Computer;
import com.laioffer.lesson.手写dagger2.Factory;

/**
 * @description 第1个注解 @Inject
 * @date
 */
public enum Computer_Factory implements Factory<Computer> {

    INSTANCE;

    @Override
    public Computer get() {
        return new Computer();
    }

    public static Factory<Computer> create() {
        return INSTANCE;
    }
}
