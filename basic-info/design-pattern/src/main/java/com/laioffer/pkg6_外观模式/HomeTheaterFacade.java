package com.laioffer.pkg6_外观模式;

public class HomeTheaterFacade {

  private final TheaterLight mTheaterLight;
  private final Popcorn mPopcorn;
  private final Stereo mStereo;
  private final Projector mProjector;
  private final Screen mScreen;
  private final DVDPlayer mDVDPlayer;


  public HomeTheaterFacade() {
    mTheaterLight = TheaterLight.getInstance();
    mPopcorn = Popcorn.getInstance();
    mStereo = Stereo.getInstance();
    mProjector = Projector.getInstance();
    mScreen = Screen.getInstance();
    mDVDPlayer = DVDPlayer.getInstance();
  }

  //////////// 操作分成 4 步 ////////////////////
  // 第1步：准备工作
  public void ready() {
    mPopcorn.on();        // 打开爆米花机器
    mPopcorn.pop();       // 制作爆米花
    mScreen.down();       // 拉下屏幕
    mProjector.on();      // 投影仪打开
    mStereo.on();         // 立体声打开
    mDVDPlayer.on();      // DVD 打开
    mTheaterLight.dim();  // 灯光调暗
  }

  // 第2步：播放
  public void play() {
    mDVDPlayer.play();
  }

  // 第3步：暂停
  public void pause() {
    mDVDPlayer.pause();
  }

  // 第4步：结束
  public void end() {
    mPopcorn.off();
    mTheaterLight.bright();
    mScreen.up();
    mProjector.off();
    mStereo.off();
    mDVDPlayer.off();
  }

}
