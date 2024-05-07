package com.laioffer.pkg6_组合模式;

public abstract class OrganizationComponent {

  private String name;
  private String des;

  public OrganizationComponent(String name, String des) {
    super();
    this.name = name;
    this.des = des;
  }

  protected void add(OrganizationComponent organizationComponent) {
    throw new UnsupportedOperationException();
  }

  protected void remove(OrganizationComponent organizationComponent) {
    throw new UnsupportedOperationException();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDes() {
    return des;
  }

  public void setDes(String des) {
    this.des = des;
  }

  protected abstract void print();

}
