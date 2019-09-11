package com.innomalist.taxi.driver.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.innomalist.taxi.common.models.Service;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ItemServiceBinding extends ViewDataBinding {
  @NonNull
  public final MaterialCheckBox checkbox;

  @NonNull
  public final TextView subtitle;

  @NonNull
  public final TextView title;

  @Bindable
  protected Service mService;

  protected ItemServiceBinding(Object _bindingComponent, View _root, int _localFieldCount,
      MaterialCheckBox checkbox, TextView subtitle, TextView title) {
    super(_bindingComponent, _root, _localFieldCount);
    this.checkbox = checkbox;
    this.subtitle = subtitle;
    this.title = title;
  }

  public abstract void setService(@Nullable Service service);

  @Nullable
  public Service getService() {
    return mService;
  }

  @NonNull
  public static ItemServiceBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_service, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ItemServiceBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ItemServiceBinding>inflateInternal(inflater, com.innomalist.taxi.driver.R.layout.item_service, root, attachToRoot, component);
  }

  @NonNull
  public static ItemServiceBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_service, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ItemServiceBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ItemServiceBinding>inflateInternal(inflater, com.innomalist.taxi.driver.R.layout.item_service, null, false, component);
  }

  public static ItemServiceBinding bind(@NonNull View view) {
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
  public static ItemServiceBinding bind(@NonNull View view, @Nullable Object component) {
    return (ItemServiceBinding)bind(component, view, com.innomalist.taxi.driver.R.layout.item_service);
  }
}
