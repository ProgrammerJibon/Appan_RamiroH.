package com.yapue.appan.fragment.foodDelivery;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yapue.appan.R;
import com.yapue.appan.activity.BaseActivity;
import com.yapue.appan.adapter.CatagoryAdapter;
import com.yapue.appan.https.HttpsRequest;
import com.yapue.appan.interfaces.Helper;
import com.yapue.appan.models.LoginDTO;
import com.yapue.appan.models.PetCatList;
import com.yapue.appan.network.NetworkManager;
import com.yapue.appan.sharedprefrence.SharedPrefrence;
import com.yapue.appan.utils.Consts;
import com.yapue.appan.utils.CustomEditText;
import com.yapue.appan.utils.ProjectUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hemant on 16/2/18.
 */

public class Catagory extends Fragment implements View.OnClickListener {

    private View view;
    private BaseActivity baseActivity;
    private RecyclerView rvType;
    private CustomEditText etSearch;
    private ArrayList<PetCatList> petList;
    private LinearLayoutManager linearLayoutManager;
    private CatagoryAdapter catagoryAdapter;
    private PetCatList petCatList;
    private SharedPrefrence prefrence;
    LoginDTO loginDTO;
    private String TAG = Catagory.class.getSimpleName();
    InterstitialAd mInterstitialAd;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mInterstitialAd = new InterstitialAd(requireActivity());
        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });
    }
    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.catagory_frag, container, false);
        prefrence = SharedPrefrence.getInstance(getActivity());
        loginDTO = prefrence.getParentUser(Consts.LOGINDTO);
        init();
        return view;
    }

    private void init() {
        rvType = (RecyclerView) view.findViewById(R.id.rvType);
        etSearch = (CustomEditText) view.findViewById(R.id.etSearch);


        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    catagoryAdapter.filter(s.toString());
                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.click_event));
        switch (view.getId()) {

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (NetworkManager.isConnectToInternet(getActivity())) {
            getPetType();
        } else {
            ProjectUtils.showLong(getActivity(), "Please enable your internet connection.");
        }
    }


    public void getPetType() {
        ProjectUtils.showProgressDialog(getActivity(), true, "Please wait....");
        new HttpsRequest(Consts.GETALLPETTYPE, getparms(), getActivity()).stringPost(TAG, new Helper() {
            @Override
            public void backResponse(boolean flag, String msg, JSONObject response) {
                if (flag) {

                    //ProjectUtils.showLong(getActivity(), msg);
                    Type listType = new TypeToken<List<PetCatList>>() {
                    }.getType();
                    try {
                        petList = new ArrayList<>();
                        petList = (ArrayList<PetCatList>) new Gson().fromJson(response.getJSONArray("data").toString(), listType);

                        catagoryAdapter = new CatagoryAdapter(getActivity(), petList);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                        rvType.setLayoutManager(mLayoutManager);
                        rvType.setItemAnimator(new DefaultItemAnimator());
                        rvType.setAdapter(catagoryAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {

                }
            }
        });
    }

    public HashMap<String, String> getparms() {
        HashMap<String, String> parms = new HashMap<>();
        parms.put(Consts.USER_ID, loginDTO.getId());
        Log.e("parms", parms.toString());
        return parms;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            baseActivity = (BaseActivity) context;
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        if (baseActivity != null)
            baseActivity.bmb1.setVisibility(View.VISIBLE);
    }
}
