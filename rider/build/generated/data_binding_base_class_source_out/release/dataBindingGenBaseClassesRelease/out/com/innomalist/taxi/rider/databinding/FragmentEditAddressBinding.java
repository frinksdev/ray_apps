package com.innomalist.taxi.rider.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.innomalist.taxi.common.models.Address;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentEditAddressBinding extends ViewDataBinding {
  @NonNull
  public final TextInputEditText textAddress;

  @NonNull
  public final TextInputLayout textLayoutAddress;

  @NonNull
  public final TextInputLayout textLayoutTitle;

  @NonNull
  public final TextInputEditText textTitle;

  @Bindable
  protected Address mAddress;

  protected FragmentEditAddressBinding(Object _bindingComponent, View _root, int _localFieldCount,
      TextInputEditText textAddress, TextInputLayout textLayoutAddress,
      TextInputLayout textLayoutTitle, TextInputEditText textTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.textAddress = textAddress;
    this.textLayoutAddress = textLayoutAddress;
    this.textLayoutTitle = textLayoutTitle;
    this.textTitle = textTitle;
  }

  public abstract void setAddress(@Nullable Address address);

  @Nullable
  public Address getAddress() {
    return mAddress;
  }

  @NonNull
  public static FragmentEditAddressBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_edit_address, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentEditAddressBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentEditAddressBinding>inflateInternal(inflater, com.innomalist.taxi.rider.R.layout.fragment_edit_address, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentEditAddressBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_edit_address, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentEditAddressBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentEditAddressBinding>inflateInternal(inflater, com.innomalist.taxi.rider.R.layout.fragment_edit_address, null, false, component);
  }

  public static FragmentEditAddressBinding bind(@NonNull View view) {
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
  public static FragmentEditAddressBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentEditAddressBinding)bind(component, view, com.innomalist.taxi.rider.R.layout.fragment_edit_address);
  }
}
