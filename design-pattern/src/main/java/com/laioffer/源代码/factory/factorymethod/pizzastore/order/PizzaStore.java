package com.laioffer.源代码.factory.factorymethod.pizzastore.order;

public class PizzaStore {

	public static void main(String[] args) {
		String loc = "bj";
		if (loc.equals("bj")) {
			//����������ζ�ĸ���Pizza
			new BJOrderPizza();
		} else {
			//�����׶ؿ�ζ�ĸ���Pizza
			new LDOrderPizza();
		}
		// TODO Auto-generated method stub
	}

}
