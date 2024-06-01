package com.laioffer.pkg12_代理模式.l2_动态代理;

public class TeacherDao implements ITeacherDao {

  @Override
  public void teach() {
    System.out.println("老师讲课中...");
  }

  @Override
  public void sayHello(String name) {
    System.out.println("hello " + name);
  }
}
