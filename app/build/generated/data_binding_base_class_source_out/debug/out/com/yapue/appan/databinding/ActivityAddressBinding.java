// Generated by data binding compiler. Do not edit!
package com.yapue.appan.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.yapue.appan.R;
import com.yapue.appan.utils.CustomTextView;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityAddressBinding extends ViewDataBinding {
  @NonNull
  public final RelativeLayout actionBarMenus;

  @NonNull
  public final LinearLayout llBack;

  @NonNull
  public final RecyclerView rvAddress;

  @NonNull
  public final CustomTextView tvHeadingText;

  @NonNull
  public final CustomTextView tvSkip;

  protected ActivityAddressBinding(Object _bindingComponent, View _root, int _localFieldCount,
      RelativeLayout actionBarMenus, LinearLayout llBack, RecyclerView rvAddress,
      CustomTextView tvHeadingText, CustomTextView tvSkip) {
    super(_bindingComponent, _root, _localFieldCount);
    this.actionBarMenus = actionBarMenus;
    this.llBack = llBack;
    this.rvAddress = rvAddress;
    this.tvHeadingText = tvHeadingText;
    this.tvSkip = tvSkip;
  }

  @NonNull
  public static ActivityAddressBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_address, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityAddressBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityAddressBinding>inflateInternal(inflater, R.layout.activity_address, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityAddressBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_address, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityAddressBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityAddressBinding>inflateInternal(inflater, R.layout.activity_address, null, false, component);
  }

  public static ActivityAddressBinding bind(@NonNull View view) {
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
  public static ActivityAddressBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityAddressBinding)bind(component, view, R.layout.activity_address);
  }
}