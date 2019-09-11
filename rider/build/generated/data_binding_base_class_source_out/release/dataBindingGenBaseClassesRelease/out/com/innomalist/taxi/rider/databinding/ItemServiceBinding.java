package com.innomalist.taxi.rider.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.innomalist.taxi.common.models.Service;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ItemServiceBinding extends ViewDataBinding {
  @NonNull
  public final ImageView image;

  @NonNull
  public final TextView textCost;

  @NonNull
  public final TextView textTitle;

  @Bindable
  protected Service mItem;

  protected ItemServiceBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ImageView image, TextView textCost, TextView textTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.image = image;
    this.textCost = textCost;
    this.textTitle = textTitle;
  }

  public abstract void setItem(@Nullable Service item);

  @Nullable
  public Service getItem() {
    return mItem;
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
    return ViewDataBinding.<ItemServiceBinding>inflateInternal(inflater, com.innomalist.taxi.rider.R.layout.item_service, root, attachToRoot, component);
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
    return ViewDataBinding.<ItemServiceBinding>inflateInternal(inflater, com.innomalist.taxi.rider.R.layout.item_service, null, false, component);
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
    return (ItemServiceBinding)bind(component, view, com.innomalist.taxi.rider.R.layout.item_service);
  }
}
