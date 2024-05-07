package com.laioffer.pkg4_代理模式.l1_静态代理;

/**
 * 代理对象，静态代理
 */
public class TeacherDaoProxy implements ITeacherDao {

  private final ITeacherDao mTarget; // 目标对象，通过接口来聚合

  // 构造器
  public TeacherDaoProxy(ITeacherDao target) {
    mTarget = target;
  }

  @Override
  public void teach() {
    System.out.println("开始代理...");
    mTarget.teach();
    System.out.println("代理结束...");
  }

}
