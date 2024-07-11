package com.laioffer.source.binding.inter;

import androidx.annotation.NonNull;

/**
 * 类描述:
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/10 17:31
 */
public interface Lifecycle {

  void addListener(@NonNull LifecycleListener listener);

  void removeListener(@NonNull LifecycleListener listener);
}
