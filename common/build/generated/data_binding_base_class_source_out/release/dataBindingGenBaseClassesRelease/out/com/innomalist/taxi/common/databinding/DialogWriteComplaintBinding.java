package com.innomalist.taxi.common.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class DialogWriteComplaintBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout customDialogLayoutDesignUserInput;

  @NonNull
  public final TextInputEditText textContent;

  @NonNull
  public final TextInputLayout textLayoutContent;

  @NonNull
  public final TextInputLayout textLayoutSubject;

  @NonNull
  public final TextInputEditText textSubject;

  protected DialogWriteComplaintBinding(Object _bindingComponent, View _root, int _localFieldCount,
      LinearLayout customDialogLayoutDesignUserInput, TextInputEditText textContent,
      TextInputLayout textLayoutContent, TextInputLayout textLayoutSubject,
      TextInputEditText textSubject) {
    super(_bindingComponent, _root, _localFieldCount);
    this.customDialogLayoutDesignUserInput = customDialogLayoutDesignUserInput;
    this.textContent = textContent;
    this.textLayoutContent = textLayoutContent;
    this.textLayoutSubject = textLayoutSubject;
    this.textSubject = textSubject;
  }

  @NonNull
  public static DialogWriteComplaintBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.dialog_write_complaint, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static DialogWriteComplaintBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<DialogWriteComplaintBinding>inflateInternal(inflater, com.innomalist.taxi.common.R.layout.dialog_write_complaint, root, attachToRoot, component);
  }

  @NonNull
  public static DialogWriteComplaintBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.dialog_write_complaint, null, false, component)
   */
  @NonNull
  @Deprecated
  public static DialogWriteComplaintBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<DialogWriteComplaintBinding>inflateInternal(inflater, com.innomalist.taxi.common.R.layout.dialog_write_complaint, null, false, component);
  }

  public static DialogWriteComplaintBinding bind(@NonNull View view) {
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
  public static DialogWriteComplaintBinding bind(@NonNull View view, @Nullable Object component) {
    return (DialogWriteComplaintBinding)bind(component, view, com.innomalist.taxi.common.R.layout.dialog_write_complaint);
  }
}
