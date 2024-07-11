package com.laioffer.状态模式.demo1;

/**
 * 类描述: 正常状态：具体状态类
 * <p>
 * created by 春夏秋冬在中南 on 2023/12/11 22:34
 */
public class NormalState extends AccountState {

  public NormalState(Account account) {
    mAccount = account;
  }

  public NormalState(AccountState state) {
    mAccount = state.mAccount;
  }

  @Override
  public void deposit(double amount) {
    mAccount.setBalance(mAccount.getBalance() + amount);
    stateCheck();
  }

  @Override
  public void withdraw(double amount) {
    mAccount.setBalance(mAccount.getBalance() - amount);
    stateCheck();
  }

  @Override
  public void computeInterest() {
    System.out.println("正常状态，无需支付利息~");
  }

  // 状态转换
  @Override
  public void stateCheck() {
    if (mAccount.getBalance() > -2000 && mAccount.getBalance() <= 0) {
      mAccount.setState(new OverdraftState(this));
    } else if (mAccount.getBalance() == -2000) {
      mAccount.setState(new RestrictedState(this));
    } else if (mAccount.getBalance() < -2000) {
      System.out.println("操作受限!!");
    }
  }
}
