package com.laioffer.责任链设计模式.demo1;

import androidx.annotation.NonNull;

import java.util.Locale;

/**
 * 类的描述: 节点1:小组长
 * Created by 春夏秋冬在中南 on 2023/5/7 00:01
 */
public class GroupLeader implements Processor {
  @Override
  public boolean process(int money, @NonNull ProcessorChain chain) {
    if (money < 1000) {
      System.out.printf(Locale.ENGLISH, "%d GroupLeader批准了\n", money);
    }

    System.out.printf(Locale.ENGLISH, "%d 超出GroupLeader权限, 需要找更高级管理层审批\n", money);
    return chain.process(money);
  }
}
