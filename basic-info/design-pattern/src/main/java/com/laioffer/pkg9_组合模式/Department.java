package com.laioffer.pkg9_组合模式;

// 最小的单位，系下面没有叶子节点了
public class Department extends OrganizationComponent {

  public Department(String name, String des) {
    super(name, des);
  }

  // add 和 remove 方法就不用写了，因为 Department 是叶子节点

  @Override
  public String getName() {
    return super.getName();
  }

  @Override
  public String getDes() {
    return super.getDes();
  }

  @Override
  protected void print() {
    System.out.println(getName());
  }

}
