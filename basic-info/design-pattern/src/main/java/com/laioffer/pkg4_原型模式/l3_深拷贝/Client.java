package com.laioffer.pkg4_原型模式.l3_深拷贝;

public class Client {

  public static void main(String[] args) throws Exception {
    DeepProtoType p = new DeepProtoType();
    p.name = "张三";
    p.deepCloneableTarget = new DeepCloneableTarget("李四", "李四对应的类");

    // 使用方式1 完成深拷贝
    DeepProtoType pCopied = (DeepProtoType) p.clone();
    System.out.println("p.name=" + p.name + ", p.deepCloneableTarget=" + p.deepCloneableTarget.hashCode());
    System.out.println("pCopied.name=" + pCopied.name + ", pCopied.deepCloneableTarget=" + pCopied.deepCloneableTarget.hashCode());
    /*
        p.name=张三, p.deepCloneableTarget=1360767589
        p2.name=张三, p2.deepCloneableTarget=873415566
     */


    // 使用方式2 完成深拷贝
    DeepProtoType1 p1 = new DeepProtoType1();
    DeepProtoType1 p1Copied = (DeepProtoType1) p1.deepClone();

    System.out.println("p.name=" + p.name + "p.deepCloneableTarget=" + p.deepCloneableTarget.hashCode());
    System.out.println("p1Copied.name=" + p1Copied.name + "p1Copied.deepCloneableTarget=" + p1Copied.deepCloneableTarget.hashCode());

  }

}
