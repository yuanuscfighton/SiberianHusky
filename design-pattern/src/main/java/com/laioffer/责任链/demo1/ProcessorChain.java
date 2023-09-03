package com.laioffer.责任链设计模式.demo1;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 类的描述: 处理器执行链
 * Created by 春夏秋冬在中南 on 2023/5/6 23:56
 */
public class ProcessorChain {

  // 处理器列表
  @NonNull
  private final List<Processor> mProcessorsList = new ArrayList<>();

  // 处理器index
  private int mIndex = 0;

  public void addProcessor(@NonNull Processor processor) {
    mProcessorsList.add(processor);
  }

  public boolean process(int money) {
    if (mIndex == mProcessorsList.size()) {
      System.out.printf(Locale.ENGLISH, "%d 太多了, 回去好好看看能不能缩减一下\n", money);
      return false;
    }
    Processor processor = mProcessorsList.get(mIndex);
    mIndex++;
    return processor.process(money, this);
  }

}
