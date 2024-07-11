package com.laioffer.pkg13_模版模式.l2_钩子方法;

public abstract class SoyaMilk {

  // 模版方法，make，模版方法可以做成 final，不让子类去覆盖
  final void make() {
    select();
    if (customerWantCondiments()) {
      addCondiments();
    }
    soak();
    beat();

  }

  // 选材料
  void select() {
    System.out.println("第1步：选择好的新鲜黄豆");
  }

  // 添加不同的配料，抽象方法，子类具体实现
  abstract void addCondiments();

  // 浸泡
  void soak() {
    System.out.println("第3步：黄豆和配料开始浸泡，需要3个小时");
  }

  void beat() {
    System.out.println("第4步：黄豆和配料放到豆浆机去打碎");
  }

  // 钩子方法，决定是否需要添加配料
  boolean customerWantCondiments() {
    return true;
  }
}
