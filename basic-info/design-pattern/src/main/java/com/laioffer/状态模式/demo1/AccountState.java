package com.laioffer.状态模式.demo1;

/**
 * 类描述: 抽象状态类
 * <p>
 * created by 春夏秋冬在中南 on 2023/12/11 22:32
 */
public abstract class AccountState {

  protected Account mAccount;

  public abstract void deposit(double amount);

  public abstract void withdraw(double amount);

  public abstract void computeInterest();

  public abstract void stateCheck();
}
