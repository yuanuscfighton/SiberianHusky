package com.laioffer.pkg1_七大原则.p3_依赖倒转.l1_原始;

/**
 * 不使用依赖倒转原则
 */
public class Client {
  public static void main(String[] args) {
    Person person = new Person();
    person.receive(new Email());
  }
}

class Email {
  public String getInfo() {
    return "[电子邮件信息] : hello world";
  }
}

/**
 * 完成人接收电子邮件的功能
 */
class Person {
  public void receive(Email email) {
    System.out.println(email.getInfo());
  }
}
/*
    问题：
      receive()方法的参数是 Email，即，直接依赖了一个具体的类。
      比如，现在获取的对象是 微信、短信、等... 就需要新增类，同时 Person 也需要增加相应的接收方法

    解决：
      引入一个抽象的接口 IReceiver，表示接收者，这样 Person 类与接口 IReceiver 发生依赖
      因为 Email、Weixin、等属于接收的范围，它们各自实现 IReceiver 接口就行，这样就符合依赖倒转原则
 */