package com.laioffer.pkgx_装饰者模式;

public class Client {

  public static void main(String[] args) {

    // 装饰者模式下的订单：2份巧克力+1份牛奶的LongBlack咖啡

    // 1.先点一份 LongBlack
    Drink order = new LongBlack();
    System.out.println("费用1: " + order.cost()); // 输出单品咖啡的价格
    System.out.println("描述1: " + order.getDescription());

    // 2.再加一份牛奶
    order = new Milk(order); // 把上面的 order 放到装饰器里。即，让 Milk 包裹（装饰）上面的单品咖啡
    System.out.println("费用2(加入1份牛奶后): " + order.cost());
    System.out.println("描述2(加入1份牛奶后): " + order.getDescription());

    // 3.再加一份巧克力
    order = new Chocolate(order);
    System.out.println("费用3(又加1份巧克力): " + order.cost());
    System.out.println("描述3(又加1份巧克力): " + order.getDescription());

    // 4.加入第二份巧克力
    order = new Chocolate(order);
    System.out.println("费用4(加第2份巧克力): " + order.cost());
    System.out.println("描述4(加第2份巧克力): " + order.getDescription());

    System.out.println("============================================================");

    Drink order2 = new DeCaf();
    System.out.println("[订单2] 费用1: " + order2.cost());
    System.out.println("[订单2] 描述1: " + order2.getDescription());

    order2 = new Milk(order2);
    System.out.println("[订单2] 费用2(加入1份牛奶): " + order2.cost());
    System.out.println("[订单2] 描述2(加入1份牛奶): " + order2.getDescription());


  }
}

/*
  装饰者模式定义：动态的将新功能附加到对象上，在对象功能扩展方面，它比继承更有弹性，装饰者模式也体现了开闭原则（OCP原则）
 */