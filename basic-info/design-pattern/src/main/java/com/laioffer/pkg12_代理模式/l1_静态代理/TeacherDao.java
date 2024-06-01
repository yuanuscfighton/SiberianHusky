package com.laioffer.pkg12_代理模式.l1_静态代理;

public class TeacherDao implements ITeacherDao {

  @Override
  public void teach() {
    System.out.println("老师讲课中...");
  }

}
