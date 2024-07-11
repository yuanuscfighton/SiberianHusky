package com.laioffer.pkg4_原型模式.l2_改进;


public class Client {

  public static void main(String[] args) {
    System.out.println("原型模式完成对象的创建");
    Sheep sheep = new Sheep("tom", 1, "白色");

    sheep.friend = new Sheep("jack", 2, "��ɫ");

    Sheep sheep2 = (Sheep) sheep.clone(); // 克隆
    Sheep sheep3 = (Sheep) sheep.clone(); // 克隆
    Sheep sheep4 = (Sheep) sheep.clone(); // 克隆
    Sheep sheep5 = (Sheep) sheep.clone(); // 克隆

    // 使用克隆，如果属性是个对象，则没有真正的被复制一份
    System.out.println("sheep2 =" + sheep2 + ", sheep2哈希值: " + sheep2.hashCode() + ", sheep2.friend=" + sheep2.friend.hashCode());
    System.out.println("sheep3 =" + sheep3 + ", sheep3哈希值: " + sheep3.hashCode() + ", sheep3.friend=" + sheep3.friend.hashCode());
    System.out.println("sheep4 =" + sheep4 + ", sheep4哈希值: " + sheep4.hashCode() + ", sheep4.friend=" + sheep4.friend.hashCode());
    System.out.println("sheep5 =" + sheep5 + ", sheep5哈希值: " + sheep5.hashCode() + ", sheep5.friend=" + sheep5.friend.hashCode());

    /*
       原型模式完成对象的创建
          sheep2 =Sheep [name=tom, age=1, color=白色, address=蒙古羊🐑], sheep2哈希值: 968514068, sheep2.friend=1360767589
          sheep3 =Sheep [name=tom, age=1, color=白色, address=蒙古羊🐑], sheep3哈希值: 818403870, sheep3.friend=1360767589
          sheep4 =Sheep [name=tom, age=1, color=白色, address=蒙古羊🐑], sheep4哈希值: 1531333864, sheep4.friend=1360767589
          sheep5 =Sheep [name=tom, age=1, color=白色, address=蒙古羊🐑], sheep5哈希值: 1468177767, sheep5.friend=1360767589
     */
  }

}
