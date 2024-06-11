package com.laioffer.pkg14_命令模式;

public class LightOnCommand implements Command {

  // 聚合 LightReceiver
  LightReceiver mLightReceiver;

  public LightOnCommand(LightReceiver light) {
    super();
    mLightReceiver = light;
  }

  @Override
  public void execute() {
    // 调用接收者的方法
    mLightReceiver.on();
  }


  @Override
  public void undo() {
    mLightReceiver.off();
  }

}
