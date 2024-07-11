package com.laioffer.pkg1_七大原则.p5_开闭原则.l1_原始;

public class Ocp {

  public static void main(String[] args) {
    GraphicEditor graphicEditor = new GraphicEditor();
    graphicEditor.drawShape(new Rectangle());
    graphicEditor.drawShape(new Circle());

    // TODO: 新增 画三角形
    graphicEditor.drawTriangle(new Triangle());
  }

}

/**
 * 用于绘图的类 --> 使用方，即，使用 Shape（各种图形）去画图
 */
class GraphicEditor {
  // 接收 Shape 对象，然后根据 type，来绘制不同的图形
  public void drawShape(Shape s) {
    if (s.mType == 1) {
      drawRectangle(s);
    } else if (s.mType == 2) {
      drawCircle(s);
    } else if (s.mType == 3) {
      // TODO: 增加 if 分支
      drawTriangle(s);
    }
  }

  public void drawRectangle(Shape r) {
    System.out.println(" 绘制矩形 ");
  }

  public void drawCircle(Shape c) {
    System.out.println(" 绘制圆形 ");
  }

  // TODO: 增加绘制三角形方法
  public void drawTriangle(Shape t) {
    System.out.println(" 绘制三角形 ");
  }
}

/**
 * 基类
 */
class Shape {
  int mType;
}

class Rectangle extends Shape {
  public Rectangle() {
    super.mType = 1;
  }
}

class Circle extends Shape {
  public Circle() {
    mType = 2;
  }
}

// TODO: 新增三角形类 --> 提供方，对扩展开放
class Triangle extends Shape {
  public Triangle() {
    mType = 3;
  }
}
