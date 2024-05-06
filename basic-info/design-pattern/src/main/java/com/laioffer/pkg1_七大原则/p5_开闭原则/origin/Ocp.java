package com.laioffer.pkg1_七大原则.p5_开闭原则.origin;

public class Ocp {
  public static void main(String[] args) {
    GraphicEditor graphicEditor = new GraphicEditor();
    graphicEditor.drawShape(new Rectangle());
    graphicEditor.drawShape(new Circle());

    graphicEditor.drawShape(new Triangle());
  }

}

// 这是一个用于绘图的类 --> 使用方（因为我们使用 Shape 来画图）
class GraphicEditor {
  // 接收 Shape 对象，然后根据 type，来绘制不同的图形
  public void drawShape(Shape s) {
    if (s.m_type == 1)
      drawRectangle(s);
    else if (s.m_type == 2)
      drawCircle(s);
    else if (s.m_type == 3) /* 使用方，违反了 对修改关闭原则 */
      drawTriangle(s);
  }

  public void drawRectangle(Shape s) {
    System.out.println(" 矩形 ");
  }

  public void drawCircle(Shape s) {
    System.out.println(" 圆形 ");
  }

  /* 使用方，违反了 对修改关闭原则 */
  public void drawTriangle(Shape s) {
    System.out.println(" 三角形 ");
  }
}

// Shape 基类
class Shape {
  int m_type;
}

class Rectangle extends Shape {
  public Rectangle() {
    super.m_type = 1;
  }
}

class Circle extends Shape {
  public Circle() {
    super.m_type = 2;
  }
}

// 新增画三角形
class Triangle extends Shape {
  public Triangle() {
    super.m_type = 3;
  }
}

/*
  分析
    1.优点：比较好理解，简单易操作
    2.缺点
      （1）违反了设计模式的 OCP 原则，即对扩展开放（提供方），对修改关闭（使用方）。
          即，当我们给类增加新功能的时候，尽量不修改代码，或者尽量少修改代码
      （2）比如，我们需要增加一个图形种类（例如，三角形），我们需要做如下修改，修改的地方比较多
 */