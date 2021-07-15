// Generated by data binding compiler. Do not edit!
package com.yapue.appan.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.yapue.appan.R;
import com.yapue.appan.utils.CustomTextView;
import com.yapue.appan.utils.CustomTextViewBold;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentSalonBinding extends ViewDataBinding {
  @NonNull
  public final RelativeLayout actionBarMenus;

  @NonNull
  public final CustomTextViewBold ctvNoData;

  @NonNull
  public final ImageView ivMCSearch;

  @NonNull
  public final LinearLayout llBack;

  @NonNull
  public final LinearLayout llMCSearch;

  @NonNull
  public final RelativeLayout rlSearch;

  @NonNull
  public final RecyclerView rvSalon;

  @NonNull
  public final SearchView searchview;

  @NonNull
  public final CustomTextView tvAddWormTitle;

  protected FragmentSalonBinding(Object _bindingComponent, View _root, int _localFieldCount,
      RelativeLayout actionBarMenus, CustomTextViewBold ctvNoData, ImageView ivMCSearch,
      LinearLayout llBack, LinearLayout llMCSearch, RelativeLayout rlSearch, RecyclerView rvSalon,
      SearchView searchview, CustomTextView tvAddWormTitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.actionBarMenus = actionBarMenus;
    this.ctvNoData = ctvNoData;
    this.ivMCSearch = ivMCSearch;
    this.llBack = llBack;
    this.llMCSearch = llMCSearch;
    this.rlSearch = rlSearch;
    this.rvSalon = rvSalon;
    this.searchview = searchview;
    this.tvAddWormTitle = tvAddWormTitle;
  }

  @NonNull
  public static FragmentSalonBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_salon, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentSalonBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentSalonBinding>inflateInternal(inflater, R.layout.fragment_salon, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentSalonBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_salon, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentSalonBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentSalonBinding>inflateInternal(inflater, R.layout.fragment_salon, null, false, component);
  }

  public static FragmentSalonBinding bind(@NonNull View view) {
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
  public static FragmentSalonBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentSalonBinding)bind(component, view, R.layout.fragment_salon);
  }
}
