package com.innomalist.taxi.rider.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.innomalist.taxi.common.models.Coupon;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ItemCouponBinding extends ViewDataBinding {
  @NonNull
  public final AppCompatButton buttonSelect;

  @NonNull
  public final CardView cardTransaction;

  @NonNull
  public final ConstraintLayout constraintHeader;

  @NonNull
  public final TextView textDay;

  @NonNull
  public final TextView textLeft;

  @NonNull
  public final TextView textTime;

  @NonNull
  public final TextView textTitle;

  @Bindable
  protected Coupon mItem;

  protected ItemCouponBinding(Object _bindingComponent, View _root, int _localFieldCount,
      AppCompatButton buttonSelect, CardView cardTransaction, ConstraintLayout constraintHeader,
      TextView textDay, TextView textLeft, TextView textTime, TextView textTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.buttonSelect = buttonSelect;
    this.cardTransaction = cardTransaction;
    this.constraintHeader = constraintHeader;
    this.textDay = textDay;
    this.textLeft = textLeft;
    this.textTime = textTime;
    this.textTitle = textTitle;
  }

  public abstract void setItem(@Nullable Coupon item);

  @Nullable
  public Coupon getItem() {
    return mItem;
  }

  @NonNull
  public static ItemCouponBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_coupon, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ItemCouponBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ItemCouponBinding>inflateInternal(inflater, com.innomalist.taxi.rider.R.layout.item_coupon, root, attachToRoot, component);
  }

  @NonNull
  public static ItemCouponBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_coupon, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ItemCouponBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ItemCouponBinding>inflateInternal(inflater, com.innomalist.taxi.rider.R.layout.item_coupon, null, false, component);
  }

  public static ItemCouponBinding bind(@NonNull View view) {
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
  public static ItemCouponBinding bind(@NonNull View view, @Nullable Object component) {
    return (ItemCouponBinding)bind(component, view, com.innomalist.taxi.rider.R.layout.item_coupon);
  }
}
