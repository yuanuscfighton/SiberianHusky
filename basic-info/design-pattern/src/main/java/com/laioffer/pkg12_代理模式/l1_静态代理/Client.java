package com.laioffer.pkg12_代理模式.l1_静态代理;

public class Client {

  public static void main(String[] args) {
    // 创建目标对象，即，被代理对象
    TeacherDao teacherDao = new TeacherDao();

    // 创建代理对象，同时将被代理对象传递给代理对象
    ITeacherDao teacherDaoProxy = new TeacherDaoProxy(teacherDao);

    // 通过“代理对象”调用“被代理对象”的方法
    // 即，执行的代理对象的方法，代理对象再去调用目标对象的方法
    teacherDaoProxy.teach();
  }
}

/*
  打印结果：
      开始代理，完成某些额外的操作 ...
      老师讲课中...
      代理结束，完成某些额外的操作 ...
 */