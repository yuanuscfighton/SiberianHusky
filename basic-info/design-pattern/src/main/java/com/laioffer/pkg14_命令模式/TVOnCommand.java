package com.laioffer.pkg14_命令模式;

public class TVOnCommand implements Command {

  // �ۺ�TVReceiver

  TVReceiver tv;

  // ������
  public TVOnCommand(TVReceiver tv) {
    super();
    this.tv = tv;
  }

  @Override
  public void execute() {
    // TODO Auto-generated method stub
    // ���ý����ߵķ���
    tv.on();
  }

  @Override
  public void undo() {
    // TODO Auto-generated method stub
    // ���ý����ߵķ���
    tv.off();
  }
}
