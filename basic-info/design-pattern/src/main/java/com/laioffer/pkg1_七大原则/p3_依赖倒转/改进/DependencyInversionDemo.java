package com.laioffer.pkg1_七大原则.p3_依赖倒转.改进;

public class DependencyInversionDemo {
  public static void main(String[] args) {
    Person person = new Person();
    person.receive(new Email());
    person.receive(new WeiXin());
  }

}

// 定义接口
interface IReceiver {
  String getInfo();
}

class Email implements IReceiver {
  public String getInfo() {
    return "[电子邮件] : hello world";
  }
}

// 增加微信消息
class WeiXin implements IReceiver {
  public String getInfo() {
    return "[微信] : hello ok";
  }
}

class Person {
  // receive方法是依赖接口
  public void receive(IReceiver receiver) {
    System.out.println(receiver.getInfo());
  }
}
