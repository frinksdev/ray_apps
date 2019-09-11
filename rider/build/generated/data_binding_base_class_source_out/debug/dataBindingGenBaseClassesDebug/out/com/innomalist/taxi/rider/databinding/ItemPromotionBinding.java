package com.innomalist.taxi.rider.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.innomalist.taxi.common.models.Promotion;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ItemPromotionBinding extends ViewDataBinding {
  @NonNull
  public final CardView cardTransaction;

  @NonNull
  public final ConstraintLayout constraintHeader;

  @NonNull
  public final ImageView imgThumb;

  @NonNull
  public final TextView textLeft;

  @NonNull
  public final TextView textTime;

  @NonNull
  public final TextView textTitle;

  @Bindable
  protected Promotion mItem;

  protected ItemPromotionBinding(Object _bindingComponent, View _root, int _localFieldCount,
      CardView cardTransaction, ConstraintLayout constraintHeader, ImageView imgThumb,
      TextView textLeft, TextView textTime, TextView textTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.cardTransaction = cardTransaction;
    this.constraintHeader = constraintHeader;
    this.imgThumb = imgThumb;
    this.textLeft = textLeft;
    this.textTime = textTime;
    this.textTitle = textTitle;
  }

  public abstract void setItem(@Nullable Promotion item);

  @Nullable
  public Promotion getItem() {
    return mItem;
  }

  @NonNull
  public static ItemPromotionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_promotion, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ItemPromotionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ItemPromotionBinding>inflateInternal(inflater, com.innomalist.taxi.rider.R.layout.item_promotion, root, attachToRoot, component);
  }

  @NonNull
  public static ItemPromotionBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_promotion, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ItemPromotionBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ItemPromotionBinding>inflateInternal(inflater, com.innomalist.taxi.rider.R.layout.item_promotion, null, false, component);
  }

  public static ItemPromotionBinding bind(@NonNull View view) {
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
  public static ItemPromotionBinding bind(@NonNull View view, @Nullable Object component) {
    return (ItemPromotionBinding)bind(component, view, com.innomalist.taxi.rider.R.layout.item_promotion);
  }
}
