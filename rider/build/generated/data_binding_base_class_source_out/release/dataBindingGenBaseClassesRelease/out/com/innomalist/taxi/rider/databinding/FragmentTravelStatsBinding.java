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
import com.innomalist.taxi.common.models.Travel;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentTravelStatsBinding extends ViewDataBinding {
  @NonNull
  public final ImageView applyCouponButton;

  @NonNull
  public final TextView balanceLabel;

  @NonNull
  public final TextView balanceText;

  @NonNull
  public final ImageView chargeAccountButton;

  @NonNull
  public final TextView costLabel;

  @NonNull
  public final TextView costText;

  @NonNull
  public final TextView distanceLabel;

  @NonNull
  public final TextView distanceText;

  @NonNull
  public final TextView etaLabel;

  @NonNull
  public final TextView etaText;

  @Bindable
  protected Travel mTravel;

  protected FragmentTravelStatsBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ImageView applyCouponButton, TextView balanceLabel, TextView balanceText,
      ImageView chargeAccountButton, TextView costLabel, TextView costText, TextView distanceLabel,
      TextView distanceText, TextView etaLabel, TextView etaText) {
    super(_bindingComponent, _root, _localFieldCount);
    this.applyCouponButton = applyCouponButton;
    this.balanceLabel = balanceLabel;
    this.balanceText = balanceText;
    this.chargeAccountButton = chargeAccountButton;
    this.costLabel = costLabel;
    this.costText = costText;
    this.distanceLabel = distanceLabel;
    this.distanceText = distanceText;
    this.etaLabel = etaLabel;
    this.etaText = etaText;
  }

  public abstract void setTravel(@Nullable Travel travel);

  @Nullable
  public Travel getTravel() {
    return mTravel;
  }

  @NonNull
  public static FragmentTravelStatsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_travel_stats, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentTravelStatsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentTravelStatsBinding>inflateInternal(inflater, com.innomalist.taxi.rider.R.layout.fragment_travel_stats, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentTravelStatsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_travel_stats, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentTravelStatsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentTravelStatsBinding>inflateInternal(inflater, com.innomalist.taxi.rider.R.layout.fragment_travel_stats, null, false, component);
  }

  public static FragmentTravelStatsBinding bind(@NonNull View view) {
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
  public static FragmentTravelStatsBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentTravelStatsBinding)bind(component, view, com.innomalist.taxi.rider.R.layout.fragment_travel_stats);
  }
}
