package com.laioffer.pkg1_七大原则.p6_迪米特法则.l1_原始;

import java.util.ArrayList;
import java.util.List;

// 客户端
public class Demeter {

  public static void main(String[] args) {
    // 创建一个学校管理对象 SchoolManager
    SchoolManager schoolManager = new SchoolManager();
    // 打印学院的员工 id 和学校总部的员工信息
    schoolManager.printAllEmployee(new CollegeManager());
  }
}


// 学校总部员工
class Employee {
  private String id;

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}


// 学院的员工
class CollegeEmployee {
  private String id;

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}


// 管理学院员工的管理类
class CollegeManager {
  // 返回学院的所有员工
  public List<CollegeEmployee> getAllEmployee() {
    List<CollegeEmployee> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) { // 增加10个员工到list集合
      CollegeEmployee emp = new CollegeEmployee();
      emp.setId("学院员工id= " + i);
      list.add(emp);
    }
    return list;
  }
}

// 学校的管理类
class SchoolManager {
  // 返回学校总部的员工
  public List<Employee> getAllEmployee() {
    List<Employee> list = new ArrayList<>();

    for (int i = 0; i < 5; i++) { // 增加5个员工到list
      Employee emp = new Employee();
      emp.setId("学校总部员工id= " + i);
      list.add(emp);
    }
    return list;
  }

  // 打印学校总部和学院员工信息的方法
  void printAllEmployee(CollegeManager sub) {
    // 获取学校员工
    List<CollegeEmployee> list1 = sub.getAllEmployee();
    System.out.println("------------学院员工------------");
    for (CollegeEmployee e : list1) {
      System.out.println(e.getId());
    }
    // 获取学校总部的员工
    List<Employee> list2 = this.getAllEmployee();
    System.out.println("------------学校总部员工------------");
    for (Employee e : list2) {
      System.out.println(e.getId());
    }
  }

  /*
    分析 SchoolManager 类的直接朋友有哪些
        （1）Employee：直接朋友，因为 Employee 是 getAllEmployee 方法的返回值
        （2）CollegeManager：直接朋友，因为 CollegeManager 是 printAllEmployee 方法的入参
        （3）CollegeEmployee：不是直接朋友，因为 CollegeEmployee 是局部变量 -> 违背了"迪米特法则"
   */
}
