package com.laioffer.源代码.observer.improve;

//�ӿ�, ��WeatherData ��ʵ�� 
public interface Subject {
	
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
}
