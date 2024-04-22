package com.laioffer.源代码.adapter.interfaceadapter;

public class Client {
	public static void main(String[] args) {
		
		AbsAdapter absAdapter = new AbsAdapter() {
			//ֻ��Ҫȥ�������� ��Ҫʹ�� �ӿڷ���
			@Override
			public void m1() {
				// TODO Auto-generated method stub
				System.out.println("ʹ����m1�ķ���");
			}
		};
		
		absAdapter.m1();
	}
}
