package com.innomalist.taxi.common.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.tylersuehr.esr.EmptyStateRecyclerView;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityTravelsBinding extends ViewDataBinding {
  @NonNull
  public final EmptyStateRecyclerView recyclerView;

  protected ActivityTravelsBinding(Object _bindingComponent, View _root, int _localFieldCount,
      EmptyStateRecyclerView recyclerView) {
    super(_bindingComponent, _root, _localFieldCount);
    this.recyclerView = recyclerView;
  }

  @NonNull
  public static ActivityTravelsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_travels, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityTravelsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityTravelsBinding>inflateInternal(inflater, com.innomalist.taxi.common.R.layout.activity_travels, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityTravelsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_travels, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityTravelsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityTravelsBinding>inflateInternal(inflater, com.innomalist.taxi.common.R.layout.activity_travels, null, false, component);
  }

  public static ActivityTravelsBinding bind(@NonNull View view) {
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
  public static ActivityTravelsBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityTravelsBinding)bind(component, view, com.innomalist.taxi.common.R.layout.activity_travels);
  }
}
