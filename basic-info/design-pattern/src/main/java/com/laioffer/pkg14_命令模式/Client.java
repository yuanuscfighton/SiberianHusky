package com.laioffer.pkg14_命令模式;

public class Client {

  public static void main(String[] args) {

    // 使用命令设计模式，完成通过遥控器，对电灯的操作
    // 创建电灯的对象（接收者）
    LightReceiver lightReceiver = new LightReceiver();

    // 创建电灯相关的开关命令
    LightOnCommand lightOnCommand = new LightOnCommand(lightReceiver);
    LightOffCommand lightOffCommand = new LightOffCommand(lightReceiver);

    //��Ҫһ��ң����
    RemoteController remoteController = new RemoteController();

    //�����ǵ�ң������������, ���� no = 0 �ǵ�ƵĿ��͹صĲ���
    remoteController.setCommand(0, lightOnCommand, lightOffCommand);

    System.out.println("--------���µƵĿ���ť-----------");
    remoteController.onButtonWasPushed(0);
    System.out.println("--------���µƵĹذ�ť-----------");
    remoteController.offButtonWasPushed(0);
    System.out.println("--------���³�����ť-----------");
    remoteController.undoButtonWasPushed();


    System.out.println("=========ʹ��ң�����������ӻ�==========");

    TVReceiver tvReceiver = new TVReceiver();

    TVOffCommand tvOffCommand = new TVOffCommand(tvReceiver);
    TVOnCommand tvOnCommand = new TVOnCommand(tvReceiver);

    //�����ǵ�ң������������, ���� no = 1 �ǵ��ӻ��Ŀ��͹صĲ���
    remoteController.setCommand(1, tvOnCommand, tvOffCommand);

    System.out.println("--------���µ��ӻ��Ŀ���ť-----------");
    remoteController.onButtonWasPushed(1);
    System.out.println("--------���µ��ӻ��Ĺذ�ť-----------");
    remoteController.offButtonWasPushed(1);
    System.out.println("--------���³�����ť-----------");
    remoteController.undoButtonWasPushed();

  }

}
