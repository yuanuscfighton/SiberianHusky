package com.laioffer.源代码.builder;

public abstract class AbstractHouse {
	
	//��ػ�
	public abstract void buildBasic();
	//��ǽ
	public abstract void buildWalls();
	//�ⶥ
	public abstract void roofed();
	
	public void build() {
		buildBasic();
		buildWalls();
		roofed();
	}
	
}
