package com.innomalist.taxi.driver.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatToggleButton;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.innomalist.taxi.common.ui.WrapContentViewPager;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityMainBinding extends ViewDataBinding {
  @NonNull
  public final Toolbar appbar;

  @NonNull
  public final AppBarLayout appbarLayout;

  @NonNull
  public final DrawerLayout drawerLayout;

  @NonNull
  public final NavigationView navigationView;

  @NonNull
  public final WrapContentViewPager requestsViewPager;

  @NonNull
  public final AppCompatToggleButton switchConnection;

  protected ActivityMainBinding(Object _bindingComponent, View _root, int _localFieldCount,
      Toolbar appbar, AppBarLayout appbarLayout, DrawerLayout drawerLayout,
      NavigationView navigationView, WrapContentViewPager requestsViewPager,
      AppCompatToggleButton switchConnection) {
    super(_bindingComponent, _root, _localFieldCount);
    this.appbar = appbar;
    this.appbarLayout = appbarLayout;
    this.drawerLayout = drawerLayout;
    this.navigationView = navigationView;
    this.requestsViewPager = requestsViewPager;
    this.switchConnection = switchConnection;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_main, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityMainBinding>inflateInternal(inflater, com.innomalist.taxi.driver.R.layout.activity_main, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_main, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityMainBinding>inflateInternal(inflater, com.innomalist.taxi.driver.R.layout.activity_main, null, false, component);
  }

  public static ActivityMainBinding bind(@NonNull View view) {
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
  public static ActivityMainBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityMainBinding)bind(component, view, com.innomalist.taxi.driver.R.layout.activity_main);
  }
}
