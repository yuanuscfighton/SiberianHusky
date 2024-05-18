package com.laioffer.pkg1_七大原则.p5_开闭原则.l1_原始;

public class Ocp {

  public static void main(String[] args) {
    GraphicEditor graphicEditor = new GraphicEditor();
    graphicEditor.drawShape(new Rectangle());
    graphicEditor.drawShape(new Circle());
  }

}

// 绘图的类
class GraphicEditor {
  // 接收 Shape 对象，然后根据 type，来绘制不同的图形
  public void drawShape(Shape s) {
    if (s.mType == 1) {
      drawRectangle(s);
    } else if (s.mType == 2) {
      drawCircle(s);
    }
  }

  public void drawRectangle(Shape r) {
    System.out.println(" 绘制矩形 ");
  }

  public void drawCircle(Shape c) {
    System.out.println(" 绘制圆形 ");
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
