package com.innomalist.taxi.rider.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import java.lang.Deprecated;
import java.lang.Object;
import ng.max.slideview.SlideView;

public abstract class ActivityTravelBinding extends ViewDataBinding {
  @NonNull
  public final AppCompatImageView chatButton;

  @NonNull
  public final LinearLayout contactPanel;

  @NonNull
  public final AppCompatImageView enableVerificationButton;

  @NonNull
  public final SlideView slideCall;

  @NonNull
  public final SlideView slideCancel;

  @NonNull
  public final TabLayout tabLayout;

  @NonNull
  public final ViewPager viewpager;

  protected ActivityTravelBinding(Object _bindingComponent, View _root, int _localFieldCount,
      AppCompatImageView chatButton, LinearLayout contactPanel,
      AppCompatImageView enableVerificationButton, SlideView slideCall, SlideView slideCancel,
      TabLayout tabLayout, ViewPager viewpager) {
    super(_bindingComponent, _root, _localFieldCount);
    this.chatButton = chatButton;
    this.contactPanel = contactPanel;
    this.enableVerificationButton = enableVerificationButton;
    this.slideCall = slideCall;
    this.slideCancel = slideCancel;
    this.tabLayout = tabLayout;
    this.viewpager = viewpager;
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
    return ViewDataBinding.<ActivityTravelBinding>inflateInternal(inflater, com.innomalist.taxi.rider.R.layout.activity_travel, root, attachToRoot, component);
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
    return ViewDataBinding.<ActivityTravelBinding>inflateInternal(inflater, com.innomalist.taxi.rider.R.layout.activity_travel, null, false, component);
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
    return (ActivityTravelBinding)bind(component, view, com.innomalist.taxi.rider.R.layout.activity_travel);
  }
}
