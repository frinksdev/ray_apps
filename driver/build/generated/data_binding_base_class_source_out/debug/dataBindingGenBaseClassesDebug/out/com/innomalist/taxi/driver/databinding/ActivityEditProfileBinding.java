package com.innomalist.taxi.driver.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.textfield.TextInputLayout;
import com.innomalist.taxi.common.models.Driver;
import com.innomalist.taxi.common.utils.Converters;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityEditProfileBinding extends ViewDataBinding {
  @NonNull
  public final TextInputLayout addressTextLayout;

  @NonNull
  public final AppBarLayout appBarLayout;

  @NonNull
  public final AppCompatButton buttonUpload;

  @NonNull
  public final ImageView cameraHeaderImage;

  @NonNull
  public final TextInputLayout carColorTextLayout;

  @NonNull
  public final CollapsingToolbarLayout collapseToolbar;

  @NonNull
  public final RecyclerView documents;

  @NonNull
  public final TextInputLayout emailTextLayout;

  @NonNull
  public final LinearLayout fieldsLayout;

  @NonNull
  public final TextInputLayout firstNameTextLayout;

  @NonNull
  public final TextView labelNote;

  @NonNull
  public final TextView labelStatus;

  @NonNull
  public final TextInputLayout lastNameTextLayout;

  @NonNull
  public final LinearLayout layoutNote;

  @NonNull
  public final ImageView media;

  @NonNull
  public final TextInputLayout mobileTextLayout;

  @NonNull
  public final TextInputLayout plateNumTextLayout;

  @NonNull
  public final CircleImageView profileImage;

  @NonNull
  public final RecyclerView services;

  @NonNull
  public final MaterialBetterSpinner spinnerGender;

  @NonNull
  public final TextView textNote;

  @NonNull
  public final TextView textStatus;

  @NonNull
  public final Toolbar toolbar;

  @NonNull
  public final TextView uploadRideLabel;

  @Bindable
  protected Driver mUser;

  @Bindable
  protected Converters mConverter;

  protected ActivityEditProfileBinding(Object _bindingComponent, View _root, int _localFieldCount,
      TextInputLayout addressTextLayout, AppBarLayout appBarLayout, AppCompatButton buttonUpload,
      ImageView cameraHeaderImage, TextInputLayout carColorTextLayout,
      CollapsingToolbarLayout collapseToolbar, RecyclerView documents,
      TextInputLayout emailTextLayout, LinearLayout fieldsLayout,
      TextInputLayout firstNameTextLayout, TextView labelNote, TextView labelStatus,
      TextInputLayout lastNameTextLayout, LinearLayout layoutNote, ImageView media,
      TextInputLayout mobileTextLayout, TextInputLayout plateNumTextLayout,
      CircleImageView profileImage, RecyclerView services, MaterialBetterSpinner spinnerGender,
      TextView textNote, TextView textStatus, Toolbar toolbar, TextView uploadRideLabel) {
    super(_bindingComponent, _root, _localFieldCount);
    this.addressTextLayout = addressTextLayout;
    this.appBarLayout = appBarLayout;
    this.buttonUpload = buttonUpload;
    this.cameraHeaderImage = cameraHeaderImage;
    this.carColorTextLayout = carColorTextLayout;
    this.collapseToolbar = collapseToolbar;
    this.documents = documents;
    this.emailTextLayout = emailTextLayout;
    this.fieldsLayout = fieldsLayout;
    this.firstNameTextLayout = firstNameTextLayout;
    this.labelNote = labelNote;
    this.labelStatus = labelStatus;
    this.lastNameTextLayout = lastNameTextLayout;
    this.layoutNote = layoutNote;
    this.media = media;
    this.mobileTextLayout = mobileTextLayout;
    this.plateNumTextLayout = plateNumTextLayout;
    this.profileImage = profileImage;
    this.services = services;
    this.spinnerGender = spinnerGender;
    this.textNote = textNote;
    this.textStatus = textStatus;
    this.toolbar = toolbar;
    this.uploadRideLabel = uploadRideLabel;
  }

  public abstract void setUser(@Nullable Driver user);

  @Nullable
  public Driver getUser() {
    return mUser;
  }

  public abstract void setConverter(@Nullable Converters converter);

  @Nullable
  public Converters getConverter() {
    return mConverter;
  }

  @NonNull
  public static ActivityEditProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_edit_profile, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityEditProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityEditProfileBinding>inflateInternal(inflater, com.innomalist.taxi.driver.R.layout.activity_edit_profile, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityEditProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_edit_profile, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityEditProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityEditProfileBinding>inflateInternal(inflater, com.innomalist.taxi.driver.R.layout.activity_edit_profile, null, false, component);
  }

  public static ActivityEditProfileBinding bind(@NonNull View view) {
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
  public static ActivityEditProfileBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityEditProfileBinding)bind(component, view, com.innomalist.taxi.driver.R.layout.activity_edit_profile);
  }
}
