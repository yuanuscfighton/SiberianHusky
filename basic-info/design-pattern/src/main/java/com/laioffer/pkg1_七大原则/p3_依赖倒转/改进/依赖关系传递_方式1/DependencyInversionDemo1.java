package com.laioffer.pkg1_七大原则.p3_依赖倒转.改进.依赖关系传递_方式1;

/**
 * 依赖关系传递的三种方式和应用案例
 */
public class DependencyInversionDemo1 {

  public static void main(String[] args) {
    // 变量声明的类型尽量是抽象或者接口类型，这样我们的变量引用和实际对象之间，就存在一个缓冲层，利于程序扩展和优化
    ChangHong changHong = new ChangHong();
    IOpenAndClose openAndClose = new OpenAndClose();
    openAndClose.open(changHong);
  }
}

// 方式1：通过接口实现依赖的传递
// 开关的接口
interface IOpenAndClose {
  void open(ITV tv); // 抽象方法，接收接口
}

// ITV接口
interface ITV {
  void play();
}

// 实现接口
class OpenAndClose implements IOpenAndClose {
  @Override
  public void open(ITV tv) { // open方法是接口一个接口参数
    tv.play();
  }
}


class ChangHong implements ITV {

  @Override
  public void play() {
    System.out.println("[长虹电视]");
  }
}