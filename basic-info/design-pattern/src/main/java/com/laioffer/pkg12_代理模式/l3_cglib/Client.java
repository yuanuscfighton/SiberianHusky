package com.laioffer.pkg12_代理模式.l3_cglib;

import com.laioffer.pkg12_代理模式.l2_动态代理.ProxyFactory;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//����Ŀ�����
		TeacherDao target = new TeacherDao();
		//��ȡ��������󣬲��ҽ�Ŀ����󴫵ݸ��������
		TeacherDao proxyInstance = (TeacherDao)new ProxyFactory(target).getProxyInstance();

		//ִ�д������ķ���������intecept �������Ӷ�ʵ�� ��Ŀ�����ĵ���
		String res = proxyInstance.teach();
		System.out.println("res=" + res);
	}

}
