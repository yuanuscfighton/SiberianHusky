package com.laioffer.pkg1_七大原则.p2_接口隔离.改进;

public class Segregation1 {

	public static void main(String[] args) {
		A a = new A();
		a.depend1(new B()); // A类通过接口依赖B类
		a.depend2(new B());
		a.depend3(new B());

		C c = new C();
		c.depend1(new D()); // C类通过接口去依赖(使用)D类
		c.depend4(new D());
		c.depend5(new D());

	}

}

