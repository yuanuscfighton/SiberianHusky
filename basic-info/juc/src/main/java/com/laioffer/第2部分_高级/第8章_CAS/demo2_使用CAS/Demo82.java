package com.laioffer.第2部分_高级.第8章_CAS.demo2_使用CAS;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类的描述:使用原子类保证线程安全i++
 * Created by 春夏秋冬在中南 on 2023/7/29 10:11
 */
public class Demo82 {

  AtomicInteger mAtomicInteger = new AtomicInteger();

  public int getAtomicInteger() {
    return mAtomicInteger.get();
  }

  public void setAtomicInteger() {
    mAtomicInteger.getAndIncrement();
  }
}
