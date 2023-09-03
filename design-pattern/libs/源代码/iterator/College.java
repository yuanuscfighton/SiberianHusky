package com.laioffer.源代码.iterator;

import java.util.Iterator;

public interface College {
	
	public String getName();
	
	//����ϵ�ķ���
	public void addDepartment(String name, String desc);
	
	//����һ��������,����
	public Iterator  createIterator();
}
