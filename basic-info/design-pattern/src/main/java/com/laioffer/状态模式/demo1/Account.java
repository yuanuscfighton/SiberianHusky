package com.laioffer.状态模式.demo1;

/**
 * 类描述: 银行账户：环境类
 * <p>
 * created by 春夏秋冬在中南 on 2023/12/11 22:31
 */
public class Account {

  // 维持一个对抽象状态对象的引用
  private AccountState mState;

  // 开户名
  private final String mOwner;

  // 账户余额
  private double mBalance = 0;

  public Account(String owner, double init) {
    mOwner = owner;

    // 设置初始状态
    mState = new NormalState(this);

    System.out.println(mOwner + " 开户，初始金额为" + init);
    System.out.println("--------------------");
  }

  public double getBalance() {
    return mBalance;
  }

  public void setBalance(double balance) {
    mBalance = balance;
  }

  public void setState(AccountState state) {
    mState = state;
  }


  public void deposit(double amount) {
    System.out.println(mOwner + "存款" + amount);

    // 调用状态对象的 deposit() 方法
    mState.deposit(amount);

    System.out.println("现在余额为" + mBalance);
    System.out.println("现在账户状态为" + mState.getClass().getName());
    System.out.println("--------------------");
  }

  public void withdraw(double amount) {
    System.out.println(mOwner + "取款" + amount);

    // 调用状态对象的 withdraw() 方法
    mState.withdraw(amount);

    System.out.println("现在余额为" + mBalance);
    System.out.println("现在账户状态为" + mState.getClass().getName());
    System.out.println("--------------------");
  }

  public void computeInterest() {
    // 调用状态对象的 computeInterest() 方法
    mState.computeInterest();
  }
}
