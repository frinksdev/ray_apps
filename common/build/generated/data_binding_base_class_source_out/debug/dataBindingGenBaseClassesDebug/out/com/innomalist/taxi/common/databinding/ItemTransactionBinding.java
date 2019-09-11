package com.innomalist.taxi.common.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.innomalist.taxi.common.models.Transaction;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ItemTransactionBinding extends ViewDataBinding {
  @NonNull
  public final CardView cardTransaction;

  @NonNull
  public final ConstraintLayout constraintHeader;

  @NonNull
  public final TextView textAmount;

  @NonNull
  public final TextView textTime;

  @NonNull
  public final TextView textTitle;

  @NonNull
  public final TextView titleDay;

  @NonNull
  public final TextView titleMonth;

  @Bindable
  protected Transaction mItem;

  protected ItemTransactionBinding(Object _bindingComponent, View _root, int _localFieldCount,
      CardView cardTransaction, ConstraintLayout constraintHeader, TextView textAmount,
      TextView textTime, TextView textTitle, TextView titleDay, TextView titleMonth) {
    super(_bindingComponent, _root, _localFieldCount);
    this.cardTransaction = cardTransaction;
    this.constraintHeader = constraintHeader;
    this.textAmount = textAmount;
    this.textTime = textTime;
    this.textTitle = textTitle;
    this.titleDay = titleDay;
    this.titleMonth = titleMonth;
  }

  public abstract void setItem(@Nullable Transaction item);

  @Nullable
  public Transaction getItem() {
    return mItem;
  }

  @NonNull
  public static ItemTransactionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_transaction, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ItemTransactionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ItemTransactionBinding>inflateInternal(inflater, com.innomalist.taxi.common.R.layout.item_transaction, root, attachToRoot, component);
  }

  @NonNull
  public static ItemTransactionBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_transaction, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ItemTransactionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ItemTransactionBinding>inflateInternal(inflater, com.innomalist.taxi.common.R.layout.item_transaction, null, false, component);
  }

  public static ItemTransactionBinding bind(@NonNull View view) {
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
  public static ItemTransactionBinding bind(@NonNull View view, @Nullable Object component) {
    return (ItemTransactionBinding)bind(component, view, com.innomalist.taxi.common.R.layout.item_transaction);
  }
}
