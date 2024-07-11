package com.laioffer.pkg1_七大原则.p3_依赖倒转.l3_依赖关系传递.方式2;

/**
 * 依赖关系传递的三种方式和应用案例
 */
public class DependencyInversionDemo2 {
  public static void main(String[] args) {
    ITV changHong = new ChangHong();
    // 通过 OpenAndClose 构造器实现 ITV 依赖的传递
    IOpenAndClose openAndClose = new OpenAndClose(changHong);
    openAndClose.open();
  }
}


// 方式2：通过构造方法实现依赖的传递
interface IOpenAndClose {
  void open();
}

interface ITV {
  void play();
}

class OpenAndClose implements IOpenAndClose {
  public ITV tv;

  // 将接口 ITV 作为 OpenAndClose 类的构造器参数
  public OpenAndClose(ITV tv) {
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