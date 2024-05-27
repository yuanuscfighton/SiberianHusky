package com.laioffer.pkg1_七大原则.p5_开闭原则.l1_原始;

public class Ocp {

  public static void main(String[] args) {
    GraphicEditor graphicEditor = new GraphicEditor();
    graphicEditor.drawShape(new Rectangle());
    graphicEditor.drawShape(new Circle());
    graphicEditor.drawTriangle(new Triangle());
  }

}

// 绘图的类 —— 使用方
class GraphicEditor {
  // 接收 Shape 对象，然后根据 type，来绘制不同的图形
  public void drawShape(Shape s) {
    if (s.mType == 1) {
      drawRectangle(s);
    } else if (s.mType == 2) {
      drawCircle(s);
    } else if (s.mType == 3) {
      drawTriangle(s);
    }
  }

  public void drawRectangle(Shape r) {
    System.out.println(" 绘制矩形 ");
  }

  public void drawCircle(Shape c) {
    System.out.println(" 绘制圆形 ");
  }

  public void drawTriangle(Shape t) {
    System.out.println(" 绘制三角形 ");
  }
}

// 基类
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

class Triangle extends Shape {
  public Triangle() {
    mType = 3;
  }
}
