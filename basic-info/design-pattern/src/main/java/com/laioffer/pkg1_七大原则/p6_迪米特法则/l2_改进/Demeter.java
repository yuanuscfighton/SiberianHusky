package com.laioffer.pkg1_七大原则.p6_迪米特法则.l2_改进;

import java.util.ArrayList;
import java.util.List;

public class Demeter {

  public static void main(String[] args) {
    SchoolManager schoolManager = new SchoolManager();
    schoolManager.printAllEmployee(new CollegeManager());
  }
}


class Employee {
  private String id;

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}


class CollegeEmployee {
  private String id;

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}


class CollegeManager {
  public List<CollegeEmployee> getAllEmployee() {
    List<CollegeEmployee> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) { // 增加10个员工到list集合
      CollegeEmployee emp = new CollegeEmployee();
      emp.setId("学院员工id= " + i);
      list.add(emp);
    }
    return list;
  }

  // 输出学院员工的信息
  public void printEmployee() {
    List<CollegeEmployee> list1 = getAllEmployee();
    System.out.println("------------学院员工------------");
    for (CollegeEmployee e : list1) {
      System.out.println(e.getId());
    }
  }
}

class SchoolManager {
  public List<Employee> getAllEmployee() {
    List<Employee> list = new ArrayList<>();

    for (int i = 0; i < 5; i++) { // 增加5个员工到list
      Employee emp = new Employee();
      emp.setId("学校总部员工id= " + i);
      list.add(emp);
    }
    return list;
  }

  void printAllEmployee(CollegeManager sub) {

    /*
      解决问题思路：
      1.将 "输出学院的员工" 方法封装到 CollegeManager 里面 <- 最小知道原则

        // 获取学校员工
        // 不对的原因：在别人的代码里完成自己的逻辑
        List<CollegeEmployee> list1 = sub.getAllEmployee();
        System.out.println("------------学院员工------------");
        for (CollegeEmployee e : list1) {
          System.out.println(e.getId());
        }
    */
    sub.printEmployee();

    List<Employee> list2 = this.getAllEmployee();
    System.out.println("------------学校总部员工------------");
    for (Employee e : list2) {
      System.out.println(e.getId());
    }
  }
}
