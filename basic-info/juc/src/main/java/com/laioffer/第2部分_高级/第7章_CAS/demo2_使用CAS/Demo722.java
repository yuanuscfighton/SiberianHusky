package com.laioffer.第2部分_高级.第7章_CAS.demo2_使用CAS;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类的描述: compareAndSet
 * Created by 春夏秋冬在中南 on 2023/8/27 10:54
 */
public class Demo722 {

  public static void main(String[] args) {
    AtomicInteger num = new AtomicInteger(5);
    System.out.println(num.compareAndSet(5, 16) + "\t" + num.get());
    System.out.println(num.compareAndSet(5, 20) + "\t" + num.get());
  }
}

/*
    true	16
    false	16
 */
