package com.laioffer.lesson.手写dagger.apt_create_dagger2;


import com.laioffer.lesson.手写dagger.Computer;
import com.laioffer.lesson.手写dagger.ComputerComponent;
import com.laioffer.lesson.手写dagger.ComputerModule;
import com.laioffer.lesson.手写dagger.MainActivity;
import com.laioffer.lesson.手写dagger2.MembersInjector;

import javax.inject.Provider;

import dagger.internal.Preconditions;

/**
 * @description 第4个注解 Component
 * @date 2022/10/6 3:21 下午
 */
public class DaggerComputerComponent implements ComputerComponent {

  public DaggerComputerComponent(Builder builder) {
    // 下面代码是依赖第5个注解@Inject生成的
    initialize(builder);
  }

  // 第四个注解生成的代码
  private Provider<Computer> providerComputerProvider;
  private MembersInjector<MainActivity> mainActivityMembersInjector;

  private void initialize(final Builder builder) {
    // this.providerComputerProvider =
    //     ComputerModule_ProviderComputerFactory.create(builder.computerModule);
    this.mainActivityMembersInjector = MainActivity_MembersInjector.create(providerComputerProvider);
  }


  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {

    // 定义我们的包裹 是靠我们的第四个注解
    ComputerModule computerModule;

    private Builder() {}

    public ComputerComponent build() {
      if (computerModule == null) {
        computerModule = new ComputerModule();
      }

      return new DaggerComputerComponent(this);
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This
     *             method is a no-op. For more, see https://google.github.io/dagger/unused-modules.
     */
    @Deprecated
    public Builder computerModule(ComputerModule computerModule) {
      Preconditions.checkNotNull(computerModule);
      return this;
    }
  }

  // 对外提供Builder
  public static ComputerComponent create() {
    return builder().build();
  }

  // 下面代码是依赖第5个注解@Inject生成的
  // 往目标(MainActivity)中去注入
  @Override // MainActivity this
  public void inject(MainActivity mainActivity) {
    // 启动 MainActivity_MembersInjector 完成依赖注入
    mainActivityMembersInjector.injectMembers(mainActivity);
  }
}
