package com.laioffer.pkg4_代理模式.l2_动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {

  // 维护一个目标对象，Object 类型
  private final Object mTarget;

  // 构造器，对 target 进行初始化
  public ProxyFactory(Object target) {
    mTarget = target;
  }

  // 给目标对象（即，被代理对象）生成一个代理对象
  public Object getProxyInstance() {

		/*
		    public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h)

        参数1: ClassLoader loader     指定当前目标对象使用的类加载器，获取加载器的方法是固定的
        参数2: Class<?>[] interfaces  目标对象实现的接口类型，使用泛型方法确认类型
        参数3: InvocationHandler h    事件处理，执行目标对象的方法时，会触发事件处理器方法，会把当前执行的目标对象方法作为参数传入
		 */
    return Proxy.newProxyInstance(
        mTarget.getClass().getClassLoader(),
        mTarget.getClass().getInterfaces(),
        new InvocationHandler() {
          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // method: public abstract void com.laioffer.pkg4_代理模式.l2_动态代理.ITeacherDao.teach()
            System.out.println("JDK代理开始...");
            // 反射机制调用目标对象的方法
            Object returnVal = method.invoke(mTarget, args);
            System.out.println("JDK代理结束...");
            return returnVal;
          }
        });
  }


}
