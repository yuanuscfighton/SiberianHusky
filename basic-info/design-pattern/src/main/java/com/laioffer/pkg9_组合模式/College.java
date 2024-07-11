package com.laioffer.pkg9_组合模式;

import java.util.ArrayList;
import java.util.List;

public class College extends OrganizationComponent {

  // List 中存放的是 Department
  List<OrganizationComponent> mComponents = new ArrayList<>();

  public College(String name, String des) {
    super(name, des);
  }

  @Override
  protected void add(OrganizationComponent component) {
    // 实际业务中，College 的 add 方法可能和 University 的 add 方法不完全一样
    mComponents.add(component);
  }

  @Override
  protected void remove(OrganizationComponent component) {
    mComponents.remove(component);
  }

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
    System.out.println("----------" + getName() + "----------");
    for (OrganizationComponent component : mComponents) {
      component.print();
    }
  }
}
