package com.laioffer.pkg1_七大原则.p3_依赖倒转.改进.依赖关系传递_方式3;

/**
 * 依赖关系传递的三种方式和应用案例
 */
public class DependencyInversionDemo3 {
  public static void main(String[] args) {
    ITV changHong = new ChangHong();
    // 通过 IOpenAndClose # setter() 方法，进行 ITV 依赖的传递
    IOpenAndClose openAndClose = new OpenAndClose();
    openAndClose.setTv(changHong);
    openAndClose.open();

  }
}

// 方式3：通过 Setter 方法传递
interface IOpenAndClose {
  void open();

  void setTv(ITV tv);
}

interface ITV {
  void play();
}

class OpenAndClose implements IOpenAndClose {
  private ITV tv;

  public void setTv(ITV tv) {
    this.tv = tv;
  }

  public void open() {
    this.tv.play();
  }
}

class ChangHong implements ITV {
  @Override
  public void play() {
    System.out.println("[长虹电视]");
  }
}