package com.laioffer.pkg9_组合模式;

public class Client {

  public static void main(String[] args) {

    // 从大到小创建对象
    // 创建大学
    OrganizationComponent university = new University("清华大学", "中国顶尖大学");

    // 创建学院
    OrganizationComponent computerCollege = new College("计算机学院", "计算机学院");
    OrganizationComponent infoEngineerCollege = new College("信息工程学院", "信息工程学院");

    // 添加系
    computerCollege.add(new Department("软件工程", "软件工程不错"));
    computerCollege.add(new Department("网络工程", "网络工程不错"));
    computerCollege.add(new Department("计算机科学与技术", "计算机科学与技术是老牌的专业"));

    infoEngineerCollege.add(new Department("通信工程", "通信工程不好学"));
    infoEngineerCollege.add(new Department("信息工程", "信息工程好学"));

    // 将学院加入到大学里
    university.add(computerCollege);
    university.add(infoEngineerCollege);


    // university.print();
    infoEngineerCollege.print();
  }

}
