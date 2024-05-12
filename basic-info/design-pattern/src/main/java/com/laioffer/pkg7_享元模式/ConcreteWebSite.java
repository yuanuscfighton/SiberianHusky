package com.laioffer.pkg7_享元模式;

// 具体的网站
public class ConcreteWebSite extends WebSite {

  private final String mType;

  public ConcreteWebSite(String type) {
    mType = type;
  }

  @Override
  public void use(User user) {
    System.out.println("网站的发布形式: " + mType + " ��ʹ���� .. ʹ������" + user.getName());
  }
}
