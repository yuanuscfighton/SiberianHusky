package com.laioffer.pkg14_命令模式;

public class LightOffCommand implements Command {

  // 聚合 LightReceiver
  LightReceiver mLightReceiver;

  public LightOffCommand(LightReceiver light) {
    super();
    mLightReceiver = light;
  }

  @Override
  public void execute() {
    mLightReceiver.off();
  }

  @Override
  public void undo() {
    mLightReceiver.on();
  }
}
