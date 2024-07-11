package com.laioffer.source.binding;

import androidx.fragment.app.Fragment;

/**
 * 类描述:
 * <p>
 * created by 西伯利亚哈士奇 on 2024/6/10 21:59
 */
public class SupportRequestManagerFragment extends Fragment {

  private static final String TAG = "SupportRMFragment";
  private final ActivityFragmentLifecycle lifecycle;
  @Nullable private RequestManager requestManager;

  public SupportRequestManagerFragment() {
    this(new ActivityFragmentLifecycle());
  }

  @VisibleForTesting
  @SuppressLint("ValidFragment")
  public SupportRequestManagerFragment(@NonNull ActivityFragmentLifecycle lifecycle) {
    this.lifecycle = lifecycle;
  }

  public void setRequestManager(@Nullable RequestManager requestManager) {
    this.requestManager = requestManager;
  }

  @NonNull
  public ActivityFragmentLifecycle getGlideLifecycle() {
    return lifecycle;
  }

  @Nullable
  public RequestManager getRequestManager() {
    return requestManager;
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    // this.lifecycle.addListener(requestManager);
  }

  @Override
  public void onDetach() {
    super.onDetach();
  }

  @Override
  public void onStart() {
    super.onStart();
    lifecycle.onStart();
  }

  @Override
  public void onStop() {
    super.onStop();
    lifecycle.onStop();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    lifecycle.onDestroy();
  }
}
