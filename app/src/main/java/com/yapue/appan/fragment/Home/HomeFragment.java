package com.yapue.appan.fragment.Home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yapue.appan.R;
import com.yapue.appan.SysApplication;
import com.yapue.appan.activity.BaseActivity;
import com.yapue.appan.activity.food.Cart;
import com.yapue.appan.activity.food.SearchActivity;
import com.yapue.appan.activity.home.BreedInfoHome;
import com.yapue.appan.adapter.AdapterBrand;
import com.yapue.appan.adapter.AdapterNewProductsForAnim;
import com.yapue.appan.adapter.AdapterOffers;
import com.yapue.appan.adapter.AdapterProductsForAnim;
import com.yapue.appan.adapter.Adapter_animals;
import com.yapue.appan.adapter.SlidingImage_Adapter;
import com.yapue.appan.adapter.SlidingOffer_Adapter;
import com.yapue.appan.databinding.HomeFragmentNewBinding;
import com.yapue.appan.fragment.NearBy.HostelActivity;
import com.yapue.appan.fragment.NearBy.SalonActivity;
import com.yapue.appan.fragment.NearBy.ShopActivity;
import com.yapue.appan.fragment.NearBy.TraninerActivity;
import com.yapue.appan.fragment.NearBy.VaterinaryActivity;
import com.yapue.appan.https.HttpsRequest;
import com.yapue.appan.interfaces.Helper;
import com.yapue.appan.models.BannerDTO;
import com.yapue.appan.models.CartDTO;
import com.yapue.appan.models.HomeDTO;
import com.yapue.appan.models.LoginDTO;
import com.yapue.appan.network.NetworkManager;
import com.yapue.appan.sharedprefrence.SharedPrefrence;
import com.yapue.appan.utils.Consts;
import com.yapue.appan.utils.ProjectUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private String TAG = HomeFragment.class.getCanonicalName();
    private HomeFragment mcontext;
    private HomeFragmentNewBinding binding;
    HomeDTO homeDTO;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static int currentPageOffer = 0;
    private static int NUM_PAGES_OFFER = 0;
    Timer swipeTimer;
    Handler handler;
    Runnable Update;
    //  ArrayList<ImageModel> imageModelArrayList;
    private int[] myImageList = new int[]{R.drawable.pager, R.drawable.pager, R.drawable.pager};

    private SharedPrefrence sharedPrefrence;
    private LoginDTO loginDTO;

    private ArrayList<BannerDTO> bannerDTOArrayList;

    private LinearLayoutManager layoutManager;
    private LinearLayoutManager layoutManager_products;
    private LinearLayoutManager layoutManager_offers;
    private LinearLayoutManager layoutManager_brands;

    private Adapter_animals adapter_animals;
    private AdapterProductsForAnim adapter;
    private AdapterNewProductsForAnim newProductsForAnim;
    private AdapterOffers adapterOffers;
    private AdapterBrand adapterBrand;//458066

    SysApplication sysApplication;

    BaseActivity baseActivity;
    private AdView mAdView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mcontext = HomeFragment.this;
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment_new, container, false);
        sysApplication = SysApplication.getInstance();
        bannerDTOArrayList = new ArrayList<>();
        bannerDTOArrayList = populateList();
        sharedPrefrence = SharedPrefrence.getInstance(baseActivity);
        loginDTO = sharedPrefrence.getParentUser(Consts.LOGINDTO);

        if (sysApplication.getHomeDTO() != null) {
            homeDTO = sysApplication.getHomeDTO();
            setHomeAdapterData();
        }

        listners();

        ///

        mAdView = binding.adView;
//        mAdView.setAdSize(AdSize.BANNER);
//        mAdView.setAdUnitId(getString(R.string.banner_home));

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
//                // Check the LogCat to get your test device ID
//                .addTestDevice("C04B1BFFB0774708339BC273F8A43708")
                .build();

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdClosed() {
//                Toast.makeText(getApplicationContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
//                Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
//                Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });

        mAdView.loadAd(adRequest);
        ///


        return binding.getRoot();
    }

    private void listners() {
        binding.openDrawer.setOnClickListener(v -> {
            DrawerLayout drawerLayout = baseActivity.findViewById(R.id.MainActivity);
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawers();
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        binding.llPetShop.setOnClickListener(this);
        binding.llVeterinarian.setOnClickListener(this);
        binding.llPetsGrooming.setOnClickListener(this);
        binding.llHostels.setOnClickListener(this);
        binding.llTrainers.setOnClickListener(this);
        binding.llBreadInfo.setOnClickListener(this);
        binding.ctvNumber.setOnClickListener(this);
        binding.llCart.setOnClickListener(this);

        binding.IVsearch.clearFocus();
        binding.IVsearch.setFocusable(false);
        binding.IVsearch.setClickable(true);

    }


    private ArrayList<BannerDTO> populateList() {

        ArrayList<BannerDTO> list = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            BannerDTO bannerDTO = new BannerDTO();
            //  bannerDTO.setImage_drawable(bannerDTOArrayList[i]);
            list.add(bannerDTO);
        }

        return list;
    }

    public void setPager() {
        binding.pager.setAdapter(new SlidingImage_Adapter(baseActivity, homeDTO.getBanner()));
        binding.pager.setCurrentItem(0);
        binding.pager.startAutoScroll();
        binding.pager.setInterval(6000);
        binding.pager.setCycle(true);

        binding.IVsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(baseActivity, SearchActivity.class));
            }
        });


        binding.indicator.setViewPager(binding.pager);
        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
//        binding.indicator.setRadius(5 * density);
        binding.indicator.setLineWidth(50);
        binding.indicator.setStrokeWidth(15);
        NUM_PAGES = homeDTO.getBanner().size();

        // Auto start of viewpager
        handler = new Handler();
        Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                binding.pager.setCurrentItem(currentPage++, true);
            }
        };
        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 5000);

        // Pager listener over indicator
        binding.indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int po, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }
    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    public void setPagerTopOffers() {
        binding.pagerTopOffers.setAdapter(new SlidingOffer_Adapter(baseActivity, homeDTO.getOffer()));
        binding.pagerTopOffers.setCurrentItem(0);
        binding.pagerTopOffers.startAutoScroll();
        binding.pagerTopOffers.setInterval(6000);
        binding.pagerTopOffers.setCycle(true);
        binding.pagerTopOffers.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                binding.pagerTopOffers.getParent().requestDisallowInterceptTouchEvent(false);//this is used for scrollview not scroll when used viewpager under it
                return false;
            }
        });

        binding.indicatorTopOffers.setViewPager(binding.pagerTopOffers);
        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
//        binding.indicatorTopOffers.setRadius(5 * density);
        binding.indicatorTopOffers.setLineWidth(50);
        binding.indicatorTopOffers.setStrokeWidth(15);
        NUM_PAGES_OFFER = homeDTO.getOffer().size();

        // Auto start of viewpager
        handler = new Handler();
        Update = new Runnable() {
            public void run() {
                if (currentPageOffer == NUM_PAGES_OFFER) {
                    currentPageOffer = 0;
                }
                binding.pagerTopOffers.setCurrentItem(currentPageOffer++, true);
            }
        };
        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 5000);

        // Pager listener over indicator
        binding.indicatorTopOffers.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPageOffer = position;

            }

            @Override
            public void onPageScrolled(int po, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
        try {
            swipeTimer.cancel();
            handler.removeCallbacks(Update);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
        try {
            swipeTimer.cancel();
            handler.removeCallbacks(Update);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
        if (NetworkManager.isConnectToInternet(baseActivity)) {
            homegetdetail();
            getMyCart();
        } else {
            ProjectUtils.showLong(baseActivity, "Please enable your internet connection.");
        }
    }

    public void homegetdetail() {

        ProjectUtils.showProgressDialog(baseActivity, false, getResources().getString(R.string.please_wait));
        new HttpsRequest(Consts.HOMEDATA, getParamshome(), baseActivity).stringPost(TAG, new Helper() {
            @Override
            public void backResponse(boolean flag, String msg, JSONObject response) {
                ProjectUtils.pauseProgressDialog();
                if (flag) {
                    try {
                        // ProjectUtils.showToast(getActivity(), msg);
                        binding.ll.setVisibility(View.VISIBLE);
                        homeDTO = new Gson().fromJson(response.getJSONObject("data").toString(), HomeDTO.class);

                        try {
                            sysApplication.setHomeDTO(homeDTO);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        setHomeAdapterData();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    ProjectUtils.showToast(baseActivity, msg);
                    binding.ll.setVisibility(View.GONE);
                }
            }
        });
    }

    protected HashMap<String, String> getParamshome() {
        HashMap<String, String> paramshome = new HashMap<>();
        paramshome.put(Consts.USER_ID, loginDTO.getId());

        return paramshome;
    }

    public void setHomeAdapterData() {
        try {
            setPager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        layoutManager = new LinearLayoutManager(baseActivity, LinearLayoutManager.HORIZONTAL, false);
        binding.rvAnimalCat.setLayoutManager(layoutManager);
        adapter_animals = new Adapter_animals(baseActivity, homeDTO.getCategory());
        binding.rvAnimalCat.setAdapter(adapter_animals);

        layoutManager_products = new LinearLayoutManager(baseActivity, LinearLayoutManager.HORIZONTAL, false);
        binding.rvProducts.setLayoutManager(layoutManager_products);
        adapter = new AdapterProductsForAnim(baseActivity, homeDTO.getPopular_products());
        binding.rvProducts.setAdapter(adapter);

        setPagerTopOffers();

        layoutManager_offers = new LinearLayoutManager(baseActivity, LinearLayoutManager.HORIZONTAL, false);
        binding.rvTopOffers.setLayoutManager(layoutManager_offers);
        adapterOffers = new AdapterOffers(baseActivity, homeDTO.getOffer());
        binding.rvTopOffers.setAdapter(adapterOffers);
        binding.rvTopOffers.setNestedScrollingEnabled(false);

        layoutManager_brands = new LinearLayoutManager(baseActivity, LinearLayoutManager.HORIZONTAL, false);
        binding.rvTopBrandsOfPetFood.setLayoutManager(layoutManager_brands);
        adapterBrand = new AdapterBrand(baseActivity, homeDTO.getBrand());
        binding.rvTopBrandsOfPetFood.setAdapter(adapterBrand);

        layoutManager_products = new LinearLayoutManager(baseActivity, LinearLayoutManager.HORIZONTAL, false);
        binding.rvNewProducts.setLayoutManager(layoutManager_products);
        newProductsForAnim = new AdapterNewProductsForAnim(baseActivity, homeDTO.getNew_Fresh());
        binding.rvNewProducts.setAdapter(newProductsForAnim);

        try {
            if (homeDTO.getContact().getName() != null) {
                binding.ctvName.setText("Name : " + homeDTO.getContact().getName());
                binding.ctvNumber.setText("Contact Number : " + homeDTO.getContact().getMobile_no());
                binding.ctvEmail.setText("Email : " + homeDTO.getContact().getEmail());
            }
        } catch (Exception e) {

        }
        try {
            if (homeDTO.getBreed().getBreed_name() != null) {

                Glide.with(baseActivity)
                        .load(homeDTO.getBreed().getImage())
                        .placeholder(R.drawable.about_us_back)
                        .into(binding.ivType);
                binding.ctvbType.setText("Know About " + homeDTO.getBreed().getBreed_name());
            } else {

            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llPetShop:
                Intent in = new Intent(baseActivity, ShopActivity.class);
                startActivity(in);
                break;
            case R.id.llVeterinarian:
                Intent in1 = new Intent(baseActivity, VaterinaryActivity.class);
                startActivity(in1);
                break;
            case R.id.llPets_Grooming:
                Intent in2 = new Intent(baseActivity, SalonActivity.class);
                startActivity(in2);
                break;
            case R.id.llHostels:
                Intent in3 = new Intent(baseActivity, HostelActivity.class);
                startActivity(in3);
                break;
            case R.id.llTrainers:
                Intent in4 = new Intent(baseActivity, TraninerActivity.class);
                startActivity(in4);
                break;
            case R.id.llCart:
                Intent in5 = new Intent(baseActivity, Cart.class);
                startActivity(in5);
                break;
            case R.id.llBreadInfo:
                Intent inBreed = new Intent(baseActivity, BreedInfoHome.class);
                inBreed.putExtra(Consts.HOME_BREED, homeDTO.getBreed());
                startActivity(inBreed);
                break;
            case R.id.ctvNumber:
                callDialog(v);
                 makecall(homeDTO.getContact().getMobile_no());
                break;
        }
    }


    public void callDialog(View view) {

        AlertDialog.Builder alertbox = new AlertDialog.Builder(view.getRootView().getContext());
        alertbox.setMessage("do you want to call on " + homeDTO.getContact().getMobile_no() + "?");
        alertbox.setTitle("Call");
        alertbox.setIcon(R.mipmap.ic_launcher);

        alertbox.setPositiveButton("Call",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0,
                                        int arg1) {
                        makecall(homeDTO.getContact().getMobile_no());

                    }
                });
        alertbox.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0,
                                        int arg1) {

                    }
                });


        alertbox.show();
    }

    private void makecall(String phone) {

       /* if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 101);
        } else {*/
        try {
            Intent my_callIntent = new Intent(Intent.ACTION_DIAL);
            my_callIntent.setData(Uri.parse("tel:" + phone));
            baseActivity.startActivity(my_callIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // }
    }

    public void getMyCart() {
        new HttpsRequest(Consts.GET_MY_CART, getparmsCart(), baseActivity).stringPost(TAG, new Helper() {
            @Override
            public void backResponse(boolean flag, String msg, JSONObject response) {
                if (flag) {
                    Type listType = new TypeToken<List<CartDTO>>() {
                    }.getType();
                    try {
                        ArrayList cartDTOList = new ArrayList<>();
                        cartDTOList = (ArrayList<CartDTO>) new Gson().fromJson(response.getJSONArray("data").toString(), listType);

                        if (cartDTOList.size() > 0) {
                            binding.tvCardcount.setVisibility(View.VISIBLE);
                            binding.tvCardcount.setText(String.valueOf(cartDTOList.size()));
                        } else {
                            binding.tvCardcount.setVisibility(View.GONE);
                            binding.tvCardcount.setText(" " + 0);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    // ProjectUtils.showLong(mContext, msg);
                    binding.tvCardcount.setVisibility(View.GONE);
                    binding.tvCardcount.setText(" " + 0);

                }
            }
        });
    }

    public HashMap<String, String> getparmsCart() {
        HashMap<String, String> parms = new HashMap<>();
        parms.put(Consts.USER_ID, loginDTO.getId());
        Log.e("parms", parms.toString());
        return parms;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        baseActivity = (BaseActivity) context;
    }
}





