package com.laioffer.pkg13_模版模式.l2_钩子方法;

public class Client {

	public static void main(String[] args) {
		// 制作红豆豆浆
		System.out.println("---- 制作红豆豆浆 ----");
		SoyaMilk redBeanSoyaMilk = new RedBeanSoyaMilk();
		redBeanSoyaMilk.make();

		System.out.println("---- 制作花生豆浆 ----");
		SoyaMilk peanutSoyaMilk = new PeanutSoyaMilk();
		peanutSoyaMilk.make();
		
		System.out.println("----����������----");
		SoyaMilk pureSoyaMilk = new PureSoyaMilk();
		pureSoyaMilk.make();
	}

}
