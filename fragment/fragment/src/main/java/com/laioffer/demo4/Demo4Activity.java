package com.laioffer.demo4;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.laioffer.demo1.Fragment1;
import com.laioffer.demo1.Fragment2;
import com.laioffer.demo3.Fragment3;
import com.laioffer.demo3.Fragment4;
import com.laioffer.fragment.fragment.R;

/**
 * 类的描述: demo4:回退栈
 * Created by 春夏秋冬在中南 on 2023/6/10 17:35
 */
public class Demo4Activity extends AppCompatActivity {

  // 提交变更时transaction.commit()的返回值
  private int stack1Id, stack2Id, stack3Id, stack4Id;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_demo4);

    Button btn1 = findViewById(R.id.btn_show_fragment1);
    btn1.setOnClickListener(v -> {
      // 将Fragment1添加到Activity的container中
      Fragment1 fragment1 = new Fragment1();
      stack2Id = addFragment(fragment1, "fragment1");
    });

    Button btn2 = findViewById(R.id.btn_show_fragment2);
    btn2.setOnClickListener(v -> {
      // 将Fragment2添加到Activity的container中
      Fragment2 fragment2 = new Fragment2();
      stack2Id = addFragment(fragment2, "fragment2");
    });

    Button btn3 = findViewById(R.id.btn_show_fragment3);
    btn3.setOnClickListener(v -> {
      Fragment3 fragment3 = new Fragment3();
      stack3Id = addFragment(fragment3, "fragment3");
    });

    Button btn4 = findViewById(R.id.btn_show_fragment4);
    btn4.setOnClickListener(v -> {
      Fragment4 fragment4 = new Fragment4();
      stack4Id = addFragment(fragment4, "fragment4");
    });

    Button btn5 = findViewById(R.id.btn_pop_back_stack);
    btn5.setOnClickListener(v -> {
      popBackStack();
    });

    Button btn6 = findViewById(R.id.btn_pop_back_stack_to_fragment2);
    btn6.setOnClickListener(v -> {
      popBackStackToFragment2_0();
    });

    Button btn7 = findViewById(R.id.btn_pop_back_stack_to_fragment2_include);
    btn7.setOnClickListener(v -> {
      popBackStackToFragment2_include();
    });
  }

  /**
   * 添加fragment到Activity中
   *
   * @param fragment 将要被添加的fragment实例
   * @param tag      和该fragment关联起来，当通过findFragmentByTag()的时候，就可以根据该tag找到这个Fragment实例
   */
  private int addFragment(@NonNull Fragment fragment, @NonNull String tag) {
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();
    ft.add(R.id.fragment_container, fragment, tag);
    return ft.commit();
  }

  /**
   * 回退栈顶fragment
   */
  private void popBackStack() {
    FragmentManager fm = getSupportFragmentManager();
    fm.popBackStack();
  }

  /**
   * fragment2(不含)以后的元素出栈
   */
  private void popBackStackToFragment2_0() {
    FragmentManager fm = getSupportFragmentManager();
    fm.popBackStack("fragment2", 0);
    // fm.popBackStack(stack1Id, 0);
  }

  /**
   * fragment2及以后的元素都弹出栈
   */
  private void popBackStackToFragment2_include() {
    FragmentManager fm = getSupportFragmentManager();
    fm.popBackStack("fragment2", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    // fm.popBackStack(stack1Id, FragmentManager.POP_BACK_STACK_INCLUSIVE);
  }

  /**
   * 回退栈
   * 1.popBackStack()  // 将最上层的操作弹出回退栈
   * 2.popBackStack(int id, int flags)
   * 3.popBackStack(String name, int flags)
   *  // id: 当提交变更时transaction.commit()的返回值
   *  // name: addToBackStack(String tag)中的tag值
   *  // flags: 有两个取值: 0或FragmentManager.POP_BACK_STACK_INCLUSIVE
   *      - 当取值0时，表示除了参数一指定这一层之上的所有层都退出栈，指定的这一层为栈顶层
   *      - 当取值POP_BACK_STACK_INCLUSIVE时，表示连着参数一指定的这一层一起退出栈
   */
}
