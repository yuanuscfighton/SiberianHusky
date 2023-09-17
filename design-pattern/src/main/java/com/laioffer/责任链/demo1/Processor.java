package com.laioffer.责任链设计模式.demo1;

import androidx.annotation.NonNull;

/**
 * 类的描述: 处理器
 * Created by 春夏秋冬在中南 on 2023/5/6 23:57
 */
public interface Processor {

  boolean process(int money, @NonNull ProcessorChain chain);
}
