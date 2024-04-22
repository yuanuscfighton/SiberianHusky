package com.laioffer.状态模式.demo1;

/**
 * 类描述: 测试类
 * <p>
 * created by 春夏秋冬在中南 on 2023/12/11 23:07
 */
public class Client {
  public static void main(String[] args) {
    Account account = new Account("张三", 0.0);

    // 1.存1000，余额1000
    account.deposit(1000);

    // 2.取2000，余额-1000
    account.withdraw(2000);

    // 3.存3000，余额2000
    account.deposit(3000);

    // 4.取4000，余额-2000
    account.withdraw(4000);

    // 5.取1000，余额-3000
    account.withdraw(1000);

    // 计算利息
    account.computeInterest();
  }
}
