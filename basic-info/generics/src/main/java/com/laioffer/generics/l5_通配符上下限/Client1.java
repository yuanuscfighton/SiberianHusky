package com.laioffer.generics.l5_通配符上下限;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述:
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/1 17:52
 */
public class Client1 {
  public static void main(String[] args) {
    List<Animal> animals = new ArrayList<>();
    List<Cat> cats = new ArrayList<>();
    List<MiniCat> miniCats = new ArrayList<>();

    showAnim(animals);
    showAnim(cats);
    // showAnim(miniCats);

  }

  // 泛型下限通配符，传递的集合类型，只能是 Cat 或者 Cat 父类类型
  public static void showAnim(List<? super Cat> list) {
    for (Object o : list) {
      System.out.println(o);
    }
  }

  public static void addMore(List<? super Cat> list) {
//     list.add(new Animal());
    list.add(new Cat());
    list.add(new MiniCat());
  }

  public static void addMoreCat(List<Cat> cats) {
    // 如果使用 addAll 方法是可以的，因为 addAll 接收的参数类型是 (Collection<? extends Cat>
    List<Cat> newCats = new ArrayList<>();
    List<MiniCat> newMiniCats = new ArrayList<>();

    cats.addAll(newCats);
    cats.addAll(newMiniCats);
  }

}

