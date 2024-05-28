package com.laioffer.pkg9_组合模式;

import java.util.ArrayList;
import java.util.List;

// University 就是 Composite 角色，可以管理 College
public class University extends OrganizationComponent {

  List<OrganizationComponent> mComponents = new ArrayList<>();

  public University(String name, String des) {
    super(name, des);
  }

  @Override
  protected void add(OrganizationComponent component) {
    mComponents.add(component);
  }

  @Override
  protected void remove(OrganizationComponent organizationComponent) {
    mComponents.remove(organizationComponent);
  }

  @Override
  public String getName() {
    return super.getName();
  }

  @Override
  public String getDes() {
    return super.getDes();
  }

  // print 方法就是输出 University 中包含的学院
  @Override
  protected void print() {
    System.out.println("----------" + getName() + "----------");
    for (OrganizationComponent component : mComponents) {
      component.print();
    }
  }

}
