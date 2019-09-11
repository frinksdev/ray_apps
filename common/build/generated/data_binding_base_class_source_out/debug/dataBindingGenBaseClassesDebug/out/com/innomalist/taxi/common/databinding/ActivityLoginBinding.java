package com.innomalist.taxi.common.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityLoginBinding extends ViewDataBinding {
  @NonNull
  public final ViewPager viewpager;

  protected ActivityLoginBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ViewPager viewpager) {
    super(_bindingComponent, _root, _localFieldCount);
    this.viewpager = viewpager;
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_login, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityLoginBinding>inflateInternal(inflater, com.innomalist.taxi.common.R.layout.activity_login, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_login, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityLoginBinding>inflateInternal(inflater, com.innomalist.taxi.common.R.layout.activity_login, null, false, component);
  }

  public static ActivityLoginBinding bind(@NonNull View view) {
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
  public static ActivityLoginBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityLoginBinding)bind(component, view, com.innomalist.taxi.common.R.layout.activity_login);
  }
}
