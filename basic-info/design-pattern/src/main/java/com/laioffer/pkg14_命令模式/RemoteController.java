package com.laioffer.pkg14_命令模式;

public class RemoteController {

  // 开 按钮的命令数组
  Command[] onCommands;
  Command[] offCommands;

  // 执行撤销的命令
  Command undoCommand;

  // 构造器，完成对按钮的初始化
  public RemoteController() {
    onCommands = new Command[5];
    offCommands = new Command[5];

    for (int i = 0; i < 5; i++) {
      onCommands[i] = new NoCommand();
      offCommands[i] = new NoCommand();
    }
  }

  // 给我们的按钮设置你需要的命令，比如有 开灯按钮，打开电视按钮，等等...
  public void setCommand(int no, Command onCommand, Command offCommand) {
    onCommands[no] = onCommand;
    offCommands[no] = offCommand;
  }

  // 按下「开」按钮
  public void onButtonWasPushed(int no) {
    // 找到你按下的「开」的按钮，并调用对应的方法，例如第1排的开灯的按钮
    onCommands[no].execute();
    // 记录这次的操作，用于撤销操作
    undoCommand = onCommands[no];
  }

  // 按下「关」按钮
  public void offButtonWasPushed(int no) {
    // 找到你按下的「关」的按钮，并调用对应的方法
    offCommands[no].execute();
    // 记录这次的操作，用于撤销
    undoCommand = offCommands[no];

  }

  // 按下「撤销」按钮
  public void undoButtonWasPushed() {
    undoCommand.undo();
  }

}
