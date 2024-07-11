package com.laioffer.pkg9_组合模式;

// 抽象的组织。认为学校、学院、系 都是一个组织，这三者是平级的关系，只是谁包含谁
public abstract class OrganizationComponent {

  // 组织的名字
  private String mName;
  // 组织的说明
  private String mDes;

  public OrganizationComponent(String name, String des) {
    super();
    mName = name;
    mDes = des;
  }

  // 添加方法
  protected void add(OrganizationComponent organizationComponent) {
    // 默认实现。这里不写成抽象方法，是因为叶子节点不应该再有 add 方法了
    throw new UnsupportedOperationException();
  }

  // 移除方法
  protected void remove(OrganizationComponent organizationComponent) {
    throw new UnsupportedOperationException();
  }

  // 打印。所有子类都应该重写该方法
  protected abstract void print();

  public String getName() {
    return mName;
  }

  public void setName(String name) {
    mName = name;
  }

  public String getDes() {
    return mDes;
  }

  public void setDes(String des) {
    mDes = des;
  }

}
