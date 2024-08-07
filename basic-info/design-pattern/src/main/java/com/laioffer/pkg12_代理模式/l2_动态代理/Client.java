package com.laioffer.pkg12_代理模式.l2_动态代理;

public class Client {

  public static void main(String[] args) {
    // 创建目标对象（被代理对象）
    ITeacherDao target = new TeacherDao();

    // 给目标对象，创建代理对象。可以转成 ITeachDao
    ITeacherDao proxyInstance = (ITeacherDao) new ProxyFactory(target).getProxyInstance();

    // class com.sun.proxy.$Proxy0 内存中动态生成的 代理对象
    System.out.println("proxyInstance: " + proxyInstance.getClass());

    // 通过代理对象，调用目标对象的方法
    proxyInstance.teach();
    proxyInstance.sayHello("张三");
  }

}
