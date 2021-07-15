package com.yapue.appan;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.yapue.appan.databinding.ActivityAddressBindingImpl;
import com.yapue.appan.databinding.ActivityBridInfoBindingImpl;
import com.yapue.appan.databinding.ActivityPromoCodeBindingImpl;
import com.yapue.appan.databinding.ActivitySearch2BindingImpl;
import com.yapue.appan.databinding.AdapterAddressBindingImpl;
import com.yapue.appan.databinding.AdapterAnimalBindingImpl;
import com.yapue.appan.databinding.AdapterBrandsOfPetFoodBindingImpl;
import com.yapue.appan.databinding.AdapterProductsForAnimBindingImpl;
import com.yapue.appan.databinding.AdapterShopBindingImpl;
import com.yapue.appan.databinding.AdapterTopOffersBindingImpl;
import com.yapue.appan.databinding.FragmentBlankBindingImpl;
import com.yapue.appan.databinding.FragmentSalonBindingImpl;
import com.yapue.appan.databinding.FragmentShopBindingImpl;
import com.yapue.appan.databinding.FragmentVaterinaryBindingImpl;
import com.yapue.appan.databinding.HomeFragmentBindingImpl;
import com.yapue.appan.databinding.HomeFragmentNewBindingImpl;
import com.yapue.appan.databinding.NearByFragBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYADDRESS = 1;

  private static final int LAYOUT_ACTIVITYBRIDINFO = 2;

  private static final int LAYOUT_ACTIVITYPROMOCODE = 3;

  private static final int LAYOUT_ACTIVITYSEARCH2 = 4;

  private static final int LAYOUT_ADAPTERADDRESS = 5;

  private static final int LAYOUT_ADAPTERANIMAL = 6;

  private static final int LAYOUT_ADAPTERBRANDSOFPETFOOD = 7;

  private static final int LAYOUT_ADAPTERPRODUCTSFORANIM = 8;

  private static final int LAYOUT_ADAPTERSHOP = 9;

  private static final int LAYOUT_ADAPTERTOPOFFERS = 10;

  private static final int LAYOUT_FRAGMENTBLANK = 11;

  private static final int LAYOUT_FRAGMENTSALON = 12;

  private static final int LAYOUT_FRAGMENTSHOP = 13;

  private static final int LAYOUT_FRAGMENTVATERINARY = 14;

  private static final int LAYOUT_HOMEFRAGMENT = 15;

  private static final int LAYOUT_HOMEFRAGMENTNEW = 16;

  private static final int LAYOUT_NEARBYFRAG = 17;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(17);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.yapue.appan.R.layout.activity_address, LAYOUT_ACTIVITYADDRESS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.yapue.appan.R.layout.activity_brid_info, LAYOUT_ACTIVITYBRIDINFO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.yapue.appan.R.layout.activity_promo_code, LAYOUT_ACTIVITYPROMOCODE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.yapue.appan.R.layout.activity_search2, LAYOUT_ACTIVITYSEARCH2);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.yapue.appan.R.layout.adapter_address, LAYOUT_ADAPTERADDRESS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.yapue.appan.R.layout.adapter_animal, LAYOUT_ADAPTERANIMAL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.yapue.appan.R.layout.adapter_brands_of_pet_food, LAYOUT_ADAPTERBRANDSOFPETFOOD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.yapue.appan.R.layout.adapter_products_for_anim, LAYOUT_ADAPTERPRODUCTSFORANIM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.yapue.appan.R.layout.adapter_shop, LAYOUT_ADAPTERSHOP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.yapue.appan.R.layout.adapter_top_offers, LAYOUT_ADAPTERTOPOFFERS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.yapue.appan.R.layout.fragment_blank, LAYOUT_FRAGMENTBLANK);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.yapue.appan.R.layout.fragment_salon, LAYOUT_FRAGMENTSALON);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.yapue.appan.R.layout.fragment_shop, LAYOUT_FRAGMENTSHOP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.yapue.appan.R.layout.fragment_vaterinary, LAYOUT_FRAGMENTVATERINARY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.yapue.appan.R.layout.home_fragment, LAYOUT_HOMEFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.yapue.appan.R.layout.home_fragment_new, LAYOUT_HOMEFRAGMENTNEW);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.yapue.appan.R.layout.near_by_frag, LAYOUT_NEARBYFRAG);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYADDRESS: {
          if ("layout/activity_address_0".equals(tag)) {
            return new ActivityAddressBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_address is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYBRIDINFO: {
          if ("layout/activity_brid_info_0".equals(tag)) {
            return new ActivityBridInfoBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_brid_info is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYPROMOCODE: {
          if ("layout/activity_promo_code_0".equals(tag)) {
            return new ActivityPromoCodeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_promo_code is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSEARCH2: {
          if ("layout/activity_search2_0".equals(tag)) {
            return new ActivitySearch2BindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_search2 is invalid. Received: " + tag);
        }
        case  LAYOUT_ADAPTERADDRESS: {
          if ("layout/adapter_address_0".equals(tag)) {
            return new AdapterAddressBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for adapter_address is invalid. Received: " + tag);
        }
        case  LAYOUT_ADAPTERANIMAL: {
          if ("layout/adapter_animal_0".equals(tag)) {
            return new AdapterAnimalBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for adapter_animal is invalid. Received: " + tag);
        }
        case  LAYOUT_ADAPTERBRANDSOFPETFOOD: {
          if ("layout/adapter_brands_of_pet_food_0".equals(tag)) {
            return new AdapterBrandsOfPetFoodBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for adapter_brands_of_pet_food is invalid. Received: " + tag);
        }
        case  LAYOUT_ADAPTERPRODUCTSFORANIM: {
          if ("layout/adapter_products_for_anim_0".equals(tag)) {
            return new AdapterProductsForAnimBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for adapter_products_for_anim is invalid. Received: " + tag);
        }
        case  LAYOUT_ADAPTERSHOP: {
          if ("layout/adapter_shop_0".equals(tag)) {
            return new AdapterShopBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for adapter_shop is invalid. Received: " + tag);
        }
        case  LAYOUT_ADAPTERTOPOFFERS: {
          if ("layout/adapter_top_offers_0".equals(tag)) {
            return new AdapterTopOffersBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for adapter_top_offers is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTBLANK: {
          if ("layout/fragment_blank_0".equals(tag)) {
            return new FragmentBlankBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_blank is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSALON: {
          if ("layout/fragment_salon_0".equals(tag)) {
            return new FragmentSalonBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_salon is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSHOP: {
          if ("layout/fragment_shop_0".equals(tag)) {
            return new FragmentShopBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_shop is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTVATERINARY: {
          if ("layout/fragment_vaterinary_0".equals(tag)) {
            return new FragmentVaterinaryBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_vaterinary is invalid. Received: " + tag);
        }
        case  LAYOUT_HOMEFRAGMENT: {
          if ("layout/home_fragment_0".equals(tag)) {
            return new HomeFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for home_fragment is invalid. Received: " + tag);
        }
        case  LAYOUT_HOMEFRAGMENTNEW: {
          if ("layout/home_fragment_new_0".equals(tag)) {
            return new HomeFragmentNewBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for home_fragment_new is invalid. Received: " + tag);
        }
        case  LAYOUT_NEARBYFRAG: {
          if ("layout/near_by_frag_0".equals(tag)) {
            return new NearByFragBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for near_by_frag is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(1);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(17);

    static {
      sKeys.put("layout/activity_address_0", com.yapue.appan.R.layout.activity_address);
      sKeys.put("layout/activity_brid_info_0", com.yapue.appan.R.layout.activity_brid_info);
      sKeys.put("layout/activity_promo_code_0", com.yapue.appan.R.layout.activity_promo_code);
      sKeys.put("layout/activity_search2_0", com.yapue.appan.R.layout.activity_search2);
      sKeys.put("layout/adapter_address_0", com.yapue.appan.R.layout.adapter_address);
      sKeys.put("layout/adapter_animal_0", com.yapue.appan.R.layout.adapter_animal);
      sKeys.put("layout/adapter_brands_of_pet_food_0", com.yapue.appan.R.layout.adapter_brands_of_pet_food);
      sKeys.put("layout/adapter_products_for_anim_0", com.yapue.appan.R.layout.adapter_products_for_anim);
      sKeys.put("layout/adapter_shop_0", com.yapue.appan.R.layout.adapter_shop);
      sKeys.put("layout/adapter_top_offers_0", com.yapue.appan.R.layout.adapter_top_offers);
      sKeys.put("layout/fragment_blank_0", com.yapue.appan.R.layout.fragment_blank);
      sKeys.put("layout/fragment_salon_0", com.yapue.appan.R.layout.fragment_salon);
      sKeys.put("layout/fragment_shop_0", com.yapue.appan.R.layout.fragment_shop);
      sKeys.put("layout/fragment_vaterinary_0", com.yapue.appan.R.layout.fragment_vaterinary);
      sKeys.put("layout/home_fragment_0", com.yapue.appan.R.layout.home_fragment);
      sKeys.put("layout/home_fragment_new_0", com.yapue.appan.R.layout.home_fragment_new);
      sKeys.put("layout/near_by_frag_0", com.yapue.appan.R.layout.near_by_frag);
    }
  }
}
