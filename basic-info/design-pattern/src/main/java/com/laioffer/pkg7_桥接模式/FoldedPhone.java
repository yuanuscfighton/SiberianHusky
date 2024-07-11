package com.laioffer.pkg7_桥接模式;


public class FoldedPhone extends Phone {

  public FoldedPhone(Brand brand) {
    super(brand);
  }

  /*
      FoldedPhone 里的 open() 方法，其实是调用 FoldedPhone 父类的 open() 方法。
      而父类中的 open() 方法 是通过 Brand # open 接口，找到 Vivo/XiaoMi 中具体的 open() 方法。
      Phone 就充当「桥」的作用
   */
  public void open() {
    super.open();
    System.out.println("折叠屏样式的手机");
  }

  public void close() {
    super.close();
    System.out.println("折叠屏样式的手机");
  }

  public void call() {
    super.call();
    System.out.println("折叠屏样式的手机");
  }
}
