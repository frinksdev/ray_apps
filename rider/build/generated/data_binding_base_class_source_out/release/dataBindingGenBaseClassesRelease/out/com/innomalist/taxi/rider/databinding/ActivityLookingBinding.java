package com.innomalist.taxi.rider.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.airbnb.lottie.LottieAnimationView;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityLookingBinding extends ViewDataBinding {
  @NonNull
  public final AppCompatButton buttonCancel;

  @NonNull
  public final LottieAnimationView loadingIndicator;

  protected ActivityLookingBinding(Object _bindingComponent, View _root, int _localFieldCount,
      AppCompatButton buttonCancel, LottieAnimationView loadingIndicator) {
    super(_bindingComponent, _root, _localFieldCount);
    this.buttonCancel = buttonCancel;
    this.loadingIndicator = loadingIndicator;
  }

  @NonNull
  public static ActivityLookingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_looking, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityLookingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityLookingBinding>inflateInternal(inflater, com.innomalist.taxi.rider.R.layout.activity_looking, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityLookingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_looking, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityLookingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityLookingBinding>inflateInternal(inflater, com.innomalist.taxi.rider.R.layout.activity_looking, null, false, component);
  }

  public static ActivityLookingBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static ActivityLookingBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityLookingBinding)bind(component, view, com.innomalist.taxi.rider.R.layout.activity_looking);
  }
}
