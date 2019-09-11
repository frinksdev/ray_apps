package com.innomalist.taxi.rider.databinding;

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

public abstract class ActivityAddressesBinding extends ViewDataBinding {
  @NonNull
  public final EmptyStateRecyclerView recyclerView;

  protected ActivityAddressesBinding(Object _bindingComponent, View _root, int _localFieldCount,
      EmptyStateRecyclerView recyclerView) {
    super(_bindingComponent, _root, _localFieldCount);
    this.recyclerView = recyclerView;
  }

  @NonNull
  public static ActivityAddressesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_addresses, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityAddressesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityAddressesBinding>inflateInternal(inflater, com.innomalist.taxi.rider.R.layout.activity_addresses, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityAddressesBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_addresses, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityAddressesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityAddressesBinding>inflateInternal(inflater, com.innomalist.taxi.rider.R.layout.activity_addresses, null, false, component);
  }

  public static ActivityAddressesBinding bind(@NonNull View view) {
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
  public static ActivityAddressesBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityAddressesBinding)bind(component, view, com.innomalist.taxi.rider.R.layout.activity_addresses);
  }
}
