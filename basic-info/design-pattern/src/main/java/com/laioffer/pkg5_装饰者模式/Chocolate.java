package com.laioffer.pkg5_装饰者模式;

//�����Decorator�� ������ǵ�ζƷ
public class Chocolate extends Decorator {

	public Chocolate(Drink obj) {
		super(obj);
		setDes(" �ɿ��� ");
		setPrice(3.0f); // ��ζƷ �ļ۸�
	}

}
