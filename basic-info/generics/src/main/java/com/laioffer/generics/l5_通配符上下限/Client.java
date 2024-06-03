package com.laioffer.generics.l5_通配符上下限;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述:
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/1 17:52
 */
public class Client {
  public static void main(String[] args) {
    List<Animal> animals = new ArrayList<>();
    List<Cat> cats = new ArrayList<>();
    List<MiniCat> miniCats = new ArrayList<>();

//    showAnim(animals);
    showAnim(cats);
    showAnim(miniCats);

    addMoreCat(cats);
  }

  // 泛型上限通配符，传递的集合类型，只能是 Cat 或者 Cat 子类类型
  public static void showAnim(List<? extends Cat> list) {
    for (int i = 0; i < list.size(); i++) {
      Cat cat = list.get(i);
      System.out.println(cat);
    }
  }

  public static void addMore(List<? extends Cat> list) {
    // list.add(new Animal());
    // list.add(new Cat());
//    list.add(new MiniCat());
    // 因为不确定 list 到底是什么类型，如果 list 是 MiniCat 类型，这里就不能添加 Cat 类型的元素了
  }

  public static void addMoreCat(List<Cat> cats) {
    // 如果使用 addAll 方法是可以的，因为 addAll 接收的参数类型是 (Collection<? extends Cat>
    List<Cat> newCats = new ArrayList<>();
    List<MiniCat> newMiniCats = new ArrayList<>();

    cats.addAll(newCats);
    cats.addAll(newMiniCats);
  }

}

