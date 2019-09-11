package com.innomalist.taxi.driver.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.innomalist.taxi.common.models.Travel;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentRequestBinding extends ViewDataBinding {
  @NonNull
  public final Button buttonAccept;

  @NonNull
  public final Button buttonDecline;

  @NonNull
  public final ImageView divider;

  @NonNull
  public final Guideline guidelineStart;

  @NonNull
  public final AppCompatImageView iconCar;

  @NonNull
  public final AppCompatImageView iconCost;

  @NonNull
  public final AppCompatImageView iconDestination;

  @NonNull
  public final AppCompatImageView iconDropoff;

  @NonNull
  public final AppCompatImageView iconPickup;

  @NonNull
  public final AppCompatImageView iconUser;

  @NonNull
  public final TextView labelCost;

  @NonNull
  public final TextView labelFrom;

  @NonNull
  public final TextView labelTo;

  @NonNull
  public final ConstraintLayout layoutTop;

  @NonNull
  public final ImageView lineDriverUser;

  @NonNull
  public final ImageView linePickupDropoff;

  @NonNull
  public final ImageView lineUserDestination;

  @NonNull
  public final TextView textCost;

  @NonNull
  public final TextView textDriverUserDistance;

  @NonNull
  public final TextView textFrom;

  @NonNull
  public final TextView textTo;

  @NonNull
  public final TextView textUserDestinationDistance;

  @Bindable
  protected Travel mTravel;

  @Bindable
  protected Integer mDistanceDriver;

  protected FragmentRequestBinding(Object _bindingComponent, View _root, int _localFieldCount,
      Button buttonAccept, Button buttonDecline, ImageView divider, Guideline guidelineStart,
      AppCompatImageView iconCar, AppCompatImageView iconCost, AppCompatImageView iconDestination,
      AppCompatImageView iconDropoff, AppCompatImageView iconPickup, AppCompatImageView iconUser,
      TextView labelCost, TextView labelFrom, TextView labelTo, ConstraintLayout layoutTop,
      ImageView lineDriverUser, ImageView linePickupDropoff, ImageView lineUserDestination,
      TextView textCost, TextView textDriverUserDistance, TextView textFrom, TextView textTo,
      TextView textUserDestinationDistance) {
    super(_bindingComponent, _root, _localFieldCount);
    this.buttonAccept = buttonAccept;
    this.buttonDecline = buttonDecline;
    this.divider = divider;
    this.guidelineStart = guidelineStart;
    this.iconCar = iconCar;
    this.iconCost = iconCost;
    this.iconDestination = iconDestination;
    this.iconDropoff = iconDropoff;
    this.iconPickup = iconPickup;
    this.iconUser = iconUser;
    this.labelCost = labelCost;
    this.labelFrom = labelFrom;
    this.labelTo = labelTo;
    this.layoutTop = layoutTop;
    this.lineDriverUser = lineDriverUser;
    this.linePickupDropoff = linePickupDropoff;
    this.lineUserDestination = lineUserDestination;
    this.textCost = textCost;
    this.textDriverUserDistance = textDriverUserDistance;
    this.textFrom = textFrom;
    this.textTo = textTo;
    this.textUserDestinationDistance = textUserDestinationDistance;
  }

  public abstract void setTravel(@Nullable Travel travel);

  @Nullable
  public Travel getTravel() {
    return mTravel;
  }

  public abstract void setDistanceDriver(@Nullable Integer distanceDriver);

  @Nullable
  public Integer getDistanceDriver() {
    return mDistanceDriver;
  }

  @NonNull
  public static FragmentRequestBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_request, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentRequestBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentRequestBinding>inflateInternal(inflater, com.innomalist.taxi.driver.R.layout.fragment_request, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentRequestBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_request, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentRequestBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentRequestBinding>inflateInternal(inflater, com.innomalist.taxi.driver.R.layout.fragment_request, null, false, component);
  }

  public static FragmentRequestBinding bind(@NonNull View view) {
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
  public static FragmentRequestBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentRequestBinding)bind(component, view, com.innomalist.taxi.driver.R.layout.fragment_request);
  }
}
