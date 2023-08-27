package com.laioffer.youtube.v4_field_inject;

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

  // if we have different Activity, we have to create a separate inject method for it.
  // the important part is argument, because theoretically use field injection to get our Engine and Wheels into the Car object,
  // instead of passing them over the constructor, but this will have a lot of downsides, for example, we cannot make Engine mEngine private.
  // entry also hide dependencies to the outside if they are not contained constructor arguments. And again if you have access to the constructor, you should use constructor injection,
  // and pass all dependencies as constructor arguments.
  //
  // General rule: Field injection is meant for framework types, data entry system instantiates like Activities and Fragments, so we don't create our MainActivity object, which means we can't do
  // constructor injection into our MainActivity. This is why we inject fields with this inject method.

  void inject(Car4Activity mainActivity);
}
