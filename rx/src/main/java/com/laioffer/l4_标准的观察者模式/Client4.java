package com.laioffer.l4_标准的观察者模式;

/**
 * 类的描述: 标准的观察者设计模式
 * <p>
 * Created by 春夏秋冬在中南 on 2023/7/30 18:43
 */
public class Client4 {

  public static void main(String[] args) {
    test();
  }

  private static void test() {
    String message = "学习android";

    // 创建一个微信公众号服务 ← 被观察者
    Observable server = new WechatServerObservable();

    // 创建用户 ← 多个观察者
    Observer zhangsan = new User("张三");
    Observer lisi = new User("李四");
    Observer wangwu = new User("王五");
    Observer zhaoliu = new User("赵六");

    // 订阅 被观察者.订阅(观察者)
    server.addObserver(zhangsan);
    server.addObserver(lisi);
    server.addObserver(wangwu);
    server.addObserver(zhaoliu);

    // 公众号内容发生变化了
    server.pushMessage(message);

    System.out.println("==========\t==========");
    // 王五取消关注了
    server.removeObserver(wangwu);
    server.pushMessage("学习Kotlin");
  }
}
