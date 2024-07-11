package com.laioffer.pkg3_工厂模式.l2_工厂方法.pizza;

public class BJPepperPizza extends Pizza {
	@Override
	public void prepare() {
		setName("北京胡椒披萨");
		System.out.println("给北京胡椒披萨准备原材料");
	}
}
