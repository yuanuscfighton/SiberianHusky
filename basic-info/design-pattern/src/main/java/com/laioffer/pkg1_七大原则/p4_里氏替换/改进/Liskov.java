package com.laioffer.pkg1_七大原则.p4_里氏替换.改进;

public class Liskov {

	public static void main(String[] args) {
		A a = new A();
		System.out.println("11-3=" + a.func1(11, 3));
		System.out.println("1-8=" + a.func1(1, 8));

		System.out.println("-----------------------");
		B b = new B();

		// 因为B类不再继承A类，因此调用者，不会再func1是求减法了，调用完成的功能就会更明确
		System.out.println("11+3=" + b.func1(11, 3));
		System.out.println("1+8=" + b.func1(1, 8));
		System.out.println("11+3+9=" + b.func2(11, 3));

		// 使用组合仍然可以使用到A类相关的方法
		System.out.println("11-3=" + b.func3(11, 3));
	}
}

// 创建一个更基础的基类
class Base {
	// 把更基础的方法和成员写到Base类
}

class A extends Base {
	public int func1(int num1, int num2) {
		return num1 - num2;
	}
}

class B extends Base {
	private final A a = new A();

	// 如果B需要使用A类的方法，使用组合关系
	public int func1(int num1, int num2) {
		return num1 + num2;
	}

	public int func2(int num1, int num2) {
		return func1(num1, num2) + 9;
	}
	
	public int func3(int num1, int num2) {
		return this.a.func1(num1, num2);
	}
}
