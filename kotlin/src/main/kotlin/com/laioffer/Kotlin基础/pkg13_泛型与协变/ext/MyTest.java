package Kotlin基础.pkg13_泛型与协变.ext;

import java.util.ArrayList;
import java.util.List;

// PECS: Producer Extends, Consumer Super.
public class MyTest {
  public static void main(String[] args) {
    List<Cat> cats = new ArrayList<>();

    // 可以存储Animal及Animal子类的类型的元素 ==> 这就是协变，只能读取，不能写入
    List<? extends Animal> animals = cats; // <-- 这行代码就是协变
    // 协变: 将一个类型赋给它的父类型，? extends Animal是Cat的父类型，因此List<? extends Animal> animals = cats; 是可以直接赋值的
    // 协变只能读取，即List.get()，不能写入，不能是List.set()
    
    // ❌ 不能编译通过. 因为当从list中取出元素的时候，无法确定元素到底是什么类型，有可能是Cat类型，还有可能是Dog类型
    // animals.add(new Cat());
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    List<Animal> animals1 = new ArrayList<>();
    List<? super Animal> contravariantAnimals = animals1;
    contravariantAnimals.add(new Cat());
    contravariantAnimals.add(new Dog());
    
    // Animal animal = contravariantAnimals.get(0);
  }
}

class Animal {

}

class Cat extends Animal {
}

class Dog extends Animal {
}
