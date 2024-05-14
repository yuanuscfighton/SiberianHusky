package com.laioffer.pkg8_工厂模式.l3_抽象工厂.pizza;

public class BJPepperPizza extends Pizza {
	@Override
	public void prepare() {
		setName("北京胡椒披萨");
		System.out.println("给北京胡椒披萨准备原材料");
	}
}
