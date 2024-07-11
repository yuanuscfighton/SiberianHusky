package com.laioffer.pkg13_模版模式.l2_钩子方法;

// 纯豆浆
public class PureSoyaMilk extends SoyaMilk {

  @Override
  void addCondiments() {
    // 不添加任何配料，空实现
  }

  @Override
  boolean customerWantCondiments() {
    return false;
  }

}
