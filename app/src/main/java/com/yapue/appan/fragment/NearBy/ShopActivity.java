package com.yapue.appan.fragment.NearBy;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yapue.appan.R;
import com.yapue.appan.adapter.AdapterShop;
import com.yapue.appan.databinding.FragmentShopBinding;
import com.yapue.appan.https.HttpsRequest;
import com.yapue.appan.interfaces.Helper;
import com.yapue.appan.models.LoginDTO;
import com.yapue.appan.models.NearByDTO;
import com.yapue.appan.network.NetworkManager;
import com.yapue.appan.sharedprefrence.SharedPrefrence;
import com.yapue.appan.utils.Consts;
import com.yapue.appan.utils.ProjectUtils;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShopActivity extends AppCompatActivity {
    Context mContext;


    FragmentShopBinding binding;
    AdapterShop adapterShop;
    private String TAG = SalonFragment.class.getSimpleName();
    private HashMap<String, String> paramsPet = new HashMap<>();
    ArrayList<NearByDTO> shopList = new ArrayList<>();
    private SharedPrefrence sharedPrefrence;
    private LoginDTO loginDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=ShopActivity.this;
        binding= DataBindingUtil.setContentView(this, R.layout.fragment_shop);

        sharedPrefrence = SharedPrefrence.getInstance(mContext);
        loginDTO = sharedPrefrence.getParentUser(Consts.LOGINDTO);

        binding.llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                try {
                    adapterShop.filter(newText);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

        //getNearByPet();
    }


    @Override
    public void onResume() {
        super.onResume();
        if (NetworkManager.isConnectToInternet(mContext)) {
            getNearByPet();
        }
    }

    public void getNearByPet() {

        paramsPet.put(Consts.NEAR_BY_ID, "3");
        paramsPet.put(Consts.USER_ID, loginDTO.getId());

        ProjectUtils.showProgressDialog(mContext, true, "Please wait....");
        new HttpsRequest(Consts.GET_ALL_NEAR_BY_VSS, paramsPet, mContext).stringPost(TAG, new Helper() {
            @Override
            public void backResponse(boolean flag, String msg, JSONObject response) {
                ProjectUtils.pauseProgressDialog();
                shopList = new ArrayList<>();

                if (flag) {
                    ProjectUtils.showLong(mContext, msg);

                    try {
                        Type listType = new TypeToken<List<NearByDTO>>() {
                        }.getType();
                        shopList = (ArrayList<NearByDTO>) new Gson().fromJson(response.getJSONArray("data").toString(), listType);

                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
                        binding.rvShop.setLayoutManager(mLayoutManager);
                        binding.rvShop.setItemAnimator(new DefaultItemAnimator());
                        adapterShop = new AdapterShop(mContext, shopList);
                        binding.rvShop.setAdapter(adapterShop);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else {
                    ProjectUtils.showLong(mContext, msg);
                    binding.ctvNoData.setVisibility(View.VISIBLE);
                }
            }
        });

    }

}
