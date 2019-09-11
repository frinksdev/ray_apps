package com.innomalist.taxi.rider.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.innomalist.taxi.common.models.Address;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ItemAddressBinding extends ViewDataBinding {
  @NonNull
  public final Button buttonDelete;

  @NonNull
  public final Button buttonEdit;

  @NonNull
  public final ImageView imageAddress;

  @NonNull
  public final ImageView imageTitle;

  @NonNull
  public final TextView textAddress;

  @NonNull
  public final TextView textTitle;

  @Bindable
  protected Address mAddress;

  protected ItemAddressBinding(Object _bindingComponent, View _root, int _localFieldCount,
      Button buttonDelete, Button buttonEdit, ImageView imageAddress, ImageView imageTitle,
      TextView textAddress, TextView textTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.buttonDelete = buttonDelete;
    this.buttonEdit = buttonEdit;
    this.imageAddress = imageAddress;
    this.imageTitle = imageTitle;
    this.textAddress = textAddress;
    this.textTitle = textTitle;
  }

  public abstract void setAddress(@Nullable Address address);

  @Nullable
  public Address getAddress() {
    return mAddress;
  }

  @NonNull
  public static ItemAddressBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_address, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ItemAddressBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ItemAddressBinding>inflateInternal(inflater, com.innomalist.taxi.rider.R.layout.item_address, root, attachToRoot, component);
  }

  @NonNull
  public static ItemAddressBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_address, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ItemAddressBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ItemAddressBinding>inflateInternal(inflater, com.innomalist.taxi.rider.R.layout.item_address, null, false, component);
  }

  public static ItemAddressBinding bind(@NonNull View view) {
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
  public static ItemAddressBinding bind(@NonNull View view, @Nullable Object component) {
    return (ItemAddressBinding)bind(component, view, com.innomalist.taxi.rider.R.layout.item_address);
  }
}
