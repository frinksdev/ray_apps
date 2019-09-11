package com.innomalist.taxi.rider.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.airbnb.lottie.LottieAnimationView;
import java.lang.Deprecated;
import java.lang.Object;
import link.fls.swipestack.SwipeStack;

public abstract class DialogRequestServiceBinding extends ViewDataBinding {
  @NonNull
  public final CardView driversAcceptedCard;

  @NonNull
  public final LottieAnimationView driversAcceptedLoadingAnimation;

  @NonNull
  public final ConstraintLayout frameLayout;

  @NonNull
  public final SwipeStack swipeStack;

  @NonNull
  public final TextView textLoading;

  protected DialogRequestServiceBinding(Object _bindingComponent, View _root, int _localFieldCount,
      CardView driversAcceptedCard, LottieAnimationView driversAcceptedLoadingAnimation,
      ConstraintLayout frameLayout, SwipeStack swipeStack, TextView textLoading) {
    super(_bindingComponent, _root, _localFieldCount);
    this.driversAcceptedCard = driversAcceptedCard;
    this.driversAcceptedLoadingAnimation = driversAcceptedLoadingAnimation;
    this.frameLayout = frameLayout;
    this.swipeStack = swipeStack;
    this.textLoading = textLoading;
  }

  @NonNull
  public static DialogRequestServiceBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.dialog_request_service, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static DialogRequestServiceBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<DialogRequestServiceBinding>inflateInternal(inflater, com.innomalist.taxi.rider.R.layout.dialog_request_service, root, attachToRoot, component);
  }

  @NonNull
  public static DialogRequestServiceBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.dialog_request_service, null, false, component)
   */
  @NonNull
  @Deprecated
  public static DialogRequestServiceBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<DialogRequestServiceBinding>inflateInternal(inflater, com.innomalist.taxi.rider.R.layout.dialog_request_service, null, false, component);
  }

  public static DialogRequestServiceBinding bind(@NonNull View view) {
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
  public static DialogRequestServiceBinding bind(@NonNull View view, @Nullable Object component) {
    return (DialogRequestServiceBinding)bind(component, view, com.innomalist.taxi.rider.R.layout.dialog_request_service);
  }
}
