package com.laioffer.pkg14_命令模式;

/**
 * 创建命令的接口
 */
public interface Command {

  // 执行动作（操作）
  void execute();

  // 撤销动作（操作）
  void undo();
}
