package com.innomalist.taxi.common.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.textfield.TextInputLayout;
import com.savvyapps.togglebuttonlayout.ToggleButtonLayout;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityChargeAccountBinding extends ViewDataBinding {
  @NonNull
  public final Button chargeAddFirst;

  @NonNull
  public final Button chargeAddSecond;

  @NonNull
  public final Button chargeAddThird;

  @NonNull
  public final AppCompatButton checkoutButton;

  @NonNull
  public final EditText editText;

  @NonNull
  public final LinearLayout layoutCharges;

  @NonNull
  public final ToggleButtonLayout paymentToggleLayout;

  @NonNull
  public final TextInputLayout priceTextLayout;

  @NonNull
  public final TextView textCurrentBalance;

  @NonNull
  public final TextView titleMethod;

  protected ActivityChargeAccountBinding(Object _bindingComponent, View _root, int _localFieldCount,
      Button chargeAddFirst, Button chargeAddSecond, Button chargeAddThird,
      AppCompatButton checkoutButton, EditText editText, LinearLayout layoutCharges,
      ToggleButtonLayout paymentToggleLayout, TextInputLayout priceTextLayout,
      TextView textCurrentBalance, TextView titleMethod) {
    super(_bindingComponent, _root, _localFieldCount);
    this.chargeAddFirst = chargeAddFirst;
    this.chargeAddSecond = chargeAddSecond;
    this.chargeAddThird = chargeAddThird;
    this.checkoutButton = checkoutButton;
    this.editText = editText;
    this.layoutCharges = layoutCharges;
    this.paymentToggleLayout = paymentToggleLayout;
    this.priceTextLayout = priceTextLayout;
    this.textCurrentBalance = textCurrentBalance;
    this.titleMethod = titleMethod;
  }

  @NonNull
  public static ActivityChargeAccountBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_charge_account, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityChargeAccountBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityChargeAccountBinding>inflateInternal(inflater, com.innomalist.taxi.common.R.layout.activity_charge_account, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityChargeAccountBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_charge_account, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityChargeAccountBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityChargeAccountBinding>inflateInternal(inflater, com.innomalist.taxi.common.R.layout.activity_charge_account, null, false, component);
  }

  public static ActivityChargeAccountBinding bind(@NonNull View view) {
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
  public static ActivityChargeAccountBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityChargeAccountBinding)bind(component, view, com.innomalist.taxi.common.R.layout.activity_charge_account);
  }
}
