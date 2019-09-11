package com.innomalist.taxi.driver.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.db.chart.view.LineChartView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.innomalist.taxi.common.models.Driver;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityStatisticsBinding extends ViewDataBinding {
  @NonNull
  public final AppBarLayout appBarLayout;

  @NonNull
  public final Button buttonPaymentRequest;

  @NonNull
  public final LineChartView chart;

  @NonNull
  public final CardView chartCard;

  @NonNull
  public final CardView incomeCard;

  @NonNull
  public final TextView incomeLabel;

  @NonNull
  public final TextView incomeText;

  @NonNull
  public final TextView labelPaymentRequest;

  @NonNull
  public final CardView paymentRequestCard;

  @NonNull
  public final ProgressBar progressPayment;

  @NonNull
  public final CardView ratingCard;

  @NonNull
  public final TextView ratingLabel;

  @NonNull
  public final TextView ratingText;

  @NonNull
  public final CardView serviceCard;

  @NonNull
  public final TextView serviceLabel;

  @NonNull
  public final TextView serviceText;

  @NonNull
  public final TabLayout tabDate;

  @NonNull
  public final TextView textPaymentBound;

  @NonNull
  public final Toolbar toolbar;

  @Bindable
  protected Driver mDriver;

  protected ActivityStatisticsBinding(Object _bindingComponent, View _root, int _localFieldCount,
      AppBarLayout appBarLayout, Button buttonPaymentRequest, LineChartView chart,
      CardView chartCard, CardView incomeCard, TextView incomeLabel, TextView incomeText,
      TextView labelPaymentRequest, CardView paymentRequestCard, ProgressBar progressPayment,
      CardView ratingCard, TextView ratingLabel, TextView ratingText, CardView serviceCard,
      TextView serviceLabel, TextView serviceText, TabLayout tabDate, TextView textPaymentBound,
      Toolbar toolbar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.appBarLayout = appBarLayout;
    this.buttonPaymentRequest = buttonPaymentRequest;
    this.chart = chart;
    this.chartCard = chartCard;
    this.incomeCard = incomeCard;
    this.incomeLabel = incomeLabel;
    this.incomeText = incomeText;
    this.labelPaymentRequest = labelPaymentRequest;
    this.paymentRequestCard = paymentRequestCard;
    this.progressPayment = progressPayment;
    this.ratingCard = ratingCard;
    this.ratingLabel = ratingLabel;
    this.ratingText = ratingText;
    this.serviceCard = serviceCard;
    this.serviceLabel = serviceLabel;
    this.serviceText = serviceText;
    this.tabDate = tabDate;
    this.textPaymentBound = textPaymentBound;
    this.toolbar = toolbar;
  }

  public abstract void setDriver(@Nullable Driver driver);

  @Nullable
  public Driver getDriver() {
    return mDriver;
  }

  @NonNull
  public static ActivityStatisticsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_statistics, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityStatisticsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityStatisticsBinding>inflateInternal(inflater, com.innomalist.taxi.driver.R.layout.activity_statistics, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityStatisticsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_statistics, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityStatisticsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityStatisticsBinding>inflateInternal(inflater, com.innomalist.taxi.driver.R.layout.activity_statistics, null, false, component);
  }

  public static ActivityStatisticsBinding bind(@NonNull View view) {
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
  public static ActivityStatisticsBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityStatisticsBinding)bind(component, view, com.innomalist.taxi.driver.R.layout.activity_statistics);
  }
}
