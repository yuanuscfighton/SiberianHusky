package com.laioffer.责任链.demo1;

/**
 * 类的描述:客户端
 * Created by 春夏秋冬在中南 on 2023/5/7 00:19
 */
public class Zhangsan {

  public static void main(String[] args) {
    int money = 2000;

    ProcessorChain chain = new ProcessorChain();
    chain.addProcessor(new GroupLeader());
    chain.addProcessor(new Manager());
    chain.addProcessor(new CEO());

    System.out.println("开发项目, 申请经费, 预算是" + money);
    boolean result = chain.process(money);
    System.out.println("处理结果: " + result);
  }
}
