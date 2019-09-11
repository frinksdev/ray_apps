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
import com.innomalist.taxi.common.models.Driver;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentTravelDriverBinding extends ViewDataBinding {
  @NonNull
  public final CircleImageView imageDriver;

  @NonNull
  public final ImageView imageLocation;

  @NonNull
  public final TextView textCarName;

  @NonNull
  public final TextView textDestination;

  @NonNull
  public final TextView textDriverName;

  @NonNull
  public final TextView textPickup;

  @Bindable
  protected Driver mDriver;

  protected FragmentTravelDriverBinding(Object _bindingComponent, View _root, int _localFieldCount,
      CircleImageView imageDriver, ImageView imageLocation, TextView textCarName,
      TextView textDestination, TextView textDriverName, TextView textPickup) {
    super(_bindingComponent, _root, _localFieldCount);
    this.imageDriver = imageDriver;
    this.imageLocation = imageLocation;
    this.textCarName = textCarName;
    this.textDestination = textDestination;
    this.textDriverName = textDriverName;
    this.textPickup = textPickup;
  }

  public abstract void setDriver(@Nullable Driver driver);

  @Nullable
  public Driver getDriver() {
    return mDriver;
  }

  @NonNull
  public static FragmentTravelDriverBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_travel_driver, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentTravelDriverBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentTravelDriverBinding>inflateInternal(inflater, com.innomalist.taxi.rider.R.layout.fragment_travel_driver, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentTravelDriverBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_travel_driver, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentTravelDriverBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentTravelDriverBinding>inflateInternal(inflater, com.innomalist.taxi.rider.R.layout.fragment_travel_driver, null, false, component);
  }

  public static FragmentTravelDriverBinding bind(@NonNull View view) {
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
  public static FragmentTravelDriverBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentTravelDriverBinding)bind(component, view, com.innomalist.taxi.rider.R.layout.fragment_travel_driver);
  }
}
