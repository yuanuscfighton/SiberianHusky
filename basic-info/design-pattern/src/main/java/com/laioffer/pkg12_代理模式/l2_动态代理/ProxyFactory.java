package com.laioffer.pkg12_代理模式.l2_动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 代理工厂
public class ProxyFactory {

  // 维护一个目标对象，Object 类型
  private final Object mTarget;

  // 构造器，对 mTarget 进行初始化
  public ProxyFactory(Object target) {
    mTarget = target;
  }

  // 根据传入的明确目标对象（即，mTarget，被代理对象），利用反射机制，返回一个代理对象
  // 通过代理对象，调用目标对象的方法
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
            // method: public abstract void com.laioffer.pkg12_代理模式.l2_动态代理.ITeacherDao.teach()
            System.out.println("JDK代理开始...");
            // 反射机制调用目标对象的方法
            Object returnVal = method.invoke(mTarget, args); // 方法参数会放在 args 里
            System.out.println("JDK代理结束...");
            return returnVal;
          }
        });
  }


}
