package com.innomalist.taxi.driver.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import java.lang.Deprecated;
import java.lang.Object;
import ng.max.slideview.SlideView;

public abstract class ActivityTravelBinding extends ViewDataBinding {
  @NonNull
  public final AppCompatImageView callButton;

  @NonNull
  public final AppCompatImageView chatButton;

  @NonNull
  public final TextView costLabel;

  @NonNull
  public final TextView costText;

  @NonNull
  public final TextView distanceLabel;

  @NonNull
  public final TextView distanceText;

  @NonNull
  public final TextView etaText;

  @NonNull
  public final AppCompatImageView inLocationButton;

  @NonNull
  public final LinearLayout layoutActions;

  @NonNull
  public final LinearLayout layoutButtons;

  @NonNull
  public final SlideView slideCancel;

  @NonNull
  public final SlideView slideFinish;

  @NonNull
  public final SlideView slideStart;

  @NonNull
  public final TextView timeLabel;

  protected ActivityTravelBinding(Object _bindingComponent, View _root, int _localFieldCount,
      AppCompatImageView callButton, AppCompatImageView chatButton, TextView costLabel,
      TextView costText, TextView distanceLabel, TextView distanceText, TextView etaText,
      AppCompatImageView inLocationButton, LinearLayout layoutActions, LinearLayout layoutButtons,
      SlideView slideCancel, SlideView slideFinish, SlideView slideStart, TextView timeLabel) {
    super(_bindingComponent, _root, _localFieldCount);
    this.callButton = callButton;
    this.chatButton = chatButton;
    this.costLabel = costLabel;
    this.costText = costText;
    this.distanceLabel = distanceLabel;
    this.distanceText = distanceText;
    this.etaText = etaText;
    this.inLocationButton = inLocationButton;
    this.layoutActions = layoutActions;
    this.layoutButtons = layoutButtons;
    this.slideCancel = slideCancel;
    this.slideFinish = slideFinish;
    this.slideStart = slideStart;
    this.timeLabel = timeLabel;
  }

  @NonNull
  public static ActivityTravelBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_travel, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityTravelBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityTravelBinding>inflateInternal(inflater, com.innomalist.taxi.driver.R.layout.activity_travel, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityTravelBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_travel, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityTravelBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityTravelBinding>inflateInternal(inflater, com.innomalist.taxi.driver.R.layout.activity_travel, null, false, component);
  }

  public static ActivityTravelBinding bind(@NonNull View view) {
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
  public static ActivityTravelBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityTravelBinding)bind(component, view, com.innomalist.taxi.driver.R.layout.activity_travel);
  }
}
