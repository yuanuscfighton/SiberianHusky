package com.laioffer.pkg1_七大原则.p5_开闭原则.l2_改进;

/**
 * 改进的思路分析：把创建 Shape 类做成抽象类，并提供一个抽象的 draw 方法，让子类去实现即可，这样我们有新的图形种类时，
 * 只需要让新的图形类继承 Shape，并实现 draw 方法即可，使用方的代码就不需要修改 -> 满足了开闭原则
 */
public class Ocp {

  public static void main(String[] args) {
    GraphicEditor graphicEditor = new GraphicEditor();
    graphicEditor.drawShape(new Rectangle());
    graphicEditor.drawShape(new Circle());
    graphicEditor.drawShape(new Triangle());
    graphicEditor.drawShape(new OtherGraphic());
  }

}

// 绘图的类 —— 使用方
// 即使我们新增一个类，使用方也没有修改
class GraphicEditor {
  // 接收 Shape 对象，然后根据 type，来绘制不同的图形
  public void drawShape(Shape s) {
    s.draw();
  }
}

abstract class Shape {
  int m_type;

  // 抽象方法
  public abstract void draw();
}

class Rectangle extends Shape {
  public Rectangle() {
    super.m_type = 1;
  }

  @Override
  public void draw() {
    System.out.println(" 绘制矩形 ");
  }
}

class Circle extends Shape {
  public Circle() {
    super.m_type = 2;
  }

  @Override
  public void draw() {
    System.out.println(" 绘制圆形 ");
  }
}

class Triangle extends Shape {
  public Triangle() {
    super.m_type = 3;
  }

  @Override
  public void draw() {
    System.out.println(" 绘制三角形 ");
  }
}

/* 新增一个图形，对扩展是开放的 */
class OtherGraphic extends Shape {
  public OtherGraphic() {
    super.m_type = 4;
  }

  @Override
  public void draw() {
    System.out.println(" 绘制其它图形 ");
  }
}
