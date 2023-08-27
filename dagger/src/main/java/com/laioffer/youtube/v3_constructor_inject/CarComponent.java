package com.laioffer.youtube.v3_constructor_inject;

// 这个类非常重要。因为像Car3Activity需要从这个类中获取Car的实例
// CarComponent create and store object, and provide them to us.
// We could call it Injector.
//
// There are 2 ways which Component can provide dependencies.
// (1) It can inject them into member variable of Car3Activity.
// (2) Provision methods. It is just a Getter() method.

import dagger.Component;

// @Component: Dagger will implement this interface at compile time.
@Component
public interface CarComponent {

  Car getCar();
}
