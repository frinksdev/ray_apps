package com.innomalist.taxi.rider.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentReviewBinding extends ViewDataBinding {
  @NonNull
  public final RatingBar ratingBar;

  @NonNull
  public final EditText reviewText;

  @NonNull
  public final TextInputLayout reviewTextLayout;

  protected FragmentReviewBinding(Object _bindingComponent, View _root, int _localFieldCount,
      RatingBar ratingBar, EditText reviewText, TextInputLayout reviewTextLayout) {
    super(_bindingComponent, _root, _localFieldCount);
    this.ratingBar = ratingBar;
    this.reviewText = reviewText;
    this.reviewTextLayout = reviewTextLayout;
  }

  @NonNull
  public static FragmentReviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_review, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentReviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentReviewBinding>inflateInternal(inflater, com.innomalist.taxi.rider.R.layout.fragment_review, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentReviewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_review, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentReviewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentReviewBinding>inflateInternal(inflater, com.innomalist.taxi.rider.R.layout.fragment_review, null, false, component);
  }

  public static FragmentReviewBinding bind(@NonNull View view) {
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
  public static FragmentReviewBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentReviewBinding)bind(component, view, com.innomalist.taxi.rider.R.layout.fragment_review);
  }
}
