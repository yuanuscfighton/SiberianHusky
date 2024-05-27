package com.laioffer.pkg6_适配器模式.l3_接口适配器;

public class Client {
  public static void main(String[] args) {

    AbsAdapter absAdapter = new AbsAdapter() {
      // 只需要 override 我们需要使用的接口方法
      @Override
      public void method1() {
        System.out.println("method1");
      }
    };

    absAdapter.method1();
  }
}

/*
  接口适配器模式介绍
  （1）接口适配器模式也叫作「缺省适配器模式」
  （2）当不需要全部实现接口提供的方法时，可以先设计一个抽象类 实现接口，并为该接口中的每个方法提供一个默认实现，那么该抽象类的子类可以有选择的覆盖父类的某些方法来实现需求
  （3）适用于一个接口不想使用其所有的方法的情况

  举例：
    android 中的属性动画 ValueAnimator 类可以通过 addListener(AnimatorListener listener)方法添加监听器。
    但有时候我们不想实现 Animator.AnimatorListener 接口的全部方法，我们只想监听 onAnimatorStart()，我们可以使用
      addListener(new AnimatorListenerAdapter() {
        @Override
        public void onAnimationStart(Animator animator) {
          // 具体实现...
        }
      })
 */