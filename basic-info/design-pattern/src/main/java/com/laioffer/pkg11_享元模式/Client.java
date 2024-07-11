package com.laioffer.pkg11_享元模式;

public class Client {

  public static void main(String[] args) {
    WebSiteFactory factory = new WebSiteFactory();

    WebSite webSite1 = factory.getWebSiteCategory("����");

    webSite1.use(new User("tom"));

    WebSite webSite2 = factory.getWebSiteCategory("����");

    webSite2.use(new User("jack"));

    WebSite webSite3 = factory.getWebSiteCategory("����");

    webSite3.use(new User("smith"));

    WebSite webSite4 = factory.getWebSiteCategory("����");

    webSite4.use(new User("king"));

    System.out.println("��վ�ķ��๲=" + factory.getWebSiteCount());
  }

}
