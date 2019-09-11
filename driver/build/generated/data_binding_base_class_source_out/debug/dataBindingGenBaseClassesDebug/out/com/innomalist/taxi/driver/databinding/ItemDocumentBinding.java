package com.innomalist.taxi.driver.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.innomalist.taxi.common.models.Media;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ItemDocumentBinding extends ViewDataBinding {
  @NonNull
  public final ImageView image;

  @Bindable
  protected Media mMedia;

  protected ItemDocumentBinding(Object _bindingComponent, View _root, int _localFieldCount,
      ImageView image) {
    super(_bindingComponent, _root, _localFieldCount);
    this.image = image;
  }

  public abstract void setMedia(@Nullable Media media);

  @Nullable
  public Media getMedia() {
    return mMedia;
  }

  @NonNull
  public static ItemDocumentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_document, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ItemDocumentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ItemDocumentBinding>inflateInternal(inflater, com.innomalist.taxi.driver.R.layout.item_document, root, attachToRoot, component);
  }

  @NonNull
  public static ItemDocumentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.item_document, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ItemDocumentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ItemDocumentBinding>inflateInternal(inflater, com.innomalist.taxi.driver.R.layout.item_document, null, false, component);
  }

  public static ItemDocumentBinding bind(@NonNull View view) {
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
  public static ItemDocumentBinding bind(@NonNull View view, @Nullable Object component) {
    return (ItemDocumentBinding)bind(component, view, com.innomalist.taxi.driver.R.layout.item_document);
  }
}
