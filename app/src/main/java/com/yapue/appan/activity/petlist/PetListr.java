package com.yapue.appan.activity.petlist;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yapue.appan.R;
import com.yapue.appan.activity.BaseActivity;
import com.yapue.appan.activity.addpet.AddPetSlides;
import com.yapue.appan.activity.filter.SearchActivity;
import com.yapue.appan.adapter.PetListAdapter;
import com.yapue.appan.fragment.AddPet.PetList;
import com.yapue.appan.https.HttpsRequest;
import com.yapue.appan.interfaces.Helper;
import com.yapue.appan.models.LoginDTO;
import com.yapue.appan.models.PetListDTO;
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

public class PetListr extends AppCompatActivity implements View.OnClickListener {
    private Context mcontext;
    private View view;
    private RecyclerView rvPetList;
    private ImageView ivNoPet, IVsearch;
    public BaseActivity baseActivity;
    private ArrayList<PetListDTO> petList;
    private LinearLayoutManager linearLayoutManager;
    private PetListAdapter petListAdapter;
    private SharedPrefrence prefrence;
    private String TAG = PetList.class.getSimpleName();
    private LoginDTO loginDTO;
    private CardView cvAddPet;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_listr);
        mcontext = PetListr.this;

        prefrence = SharedPrefrence.getInstance(mcontext);
        loginDTO = prefrence.getParentUser(Consts.LOGINDTO);
        init();
    }

    private void init() {
        rvPetList = (RecyclerView) findViewById(R.id.rvPetList);
        ivNoPet = (ImageView) findViewById(R.id.ivNoPet);
        IVsearch = (ImageView) findViewById(R.id.IVsearch);
        back = (ImageView) findViewById(R.id.back);
        cvAddPet = findViewById(R.id.cvAddPet);

        cvAddPet.setOnClickListener(this);
        IVsearch.setOnClickListener(this);
        back.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(mcontext, R.anim.click_event));
        switch (view.getId()) {
            case R.id.cvAddPet: {
                startActivity(new Intent(mcontext, AddPetSlides.class));
            }
            break;
            case R.id.IVsearch: {
                startActivity(new Intent(mcontext, SearchActivity.class));

                break;
            }
            case R.id.back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (NetworkManager.isConnectToInternet(mcontext)) {
            PetList();
        } else {
            ProjectUtils.showLong(mcontext, getResources().getString(R.string.internet_concation));
        }
    }


    public void PetList() {
        ProjectUtils.showProgressDialog(mcontext, true, "Please Wait!!");
        new HttpsRequest(Consts.GETMYPET, getparm(), mcontext).stringPost(TAG, new Helper() {
            @Override
            public void backResponse(boolean flag, String msg, JSONObject response) {
                if (flag) {
                    try {
                        petList = new ArrayList<>();
                        Type getpetDTO = new TypeToken<List<PetListDTO>>() {
                        }.getType();
                        petList = (ArrayList<PetListDTO>) new Gson().fromJson(response.getJSONArray("data").toString(), getpetDTO);
                        if (petList.size() != 0) {
                            rvPetList.setVisibility(View.VISIBLE);
                            ivNoPet.setVisibility(View.GONE);
                            petListAdapter = new PetListAdapter(mcontext, petList);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mcontext);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(mcontext, LinearLayoutManager.HORIZONTAL, false);
                            rvPetList.setLayoutManager(layoutManager);
                            rvPetList.setItemAnimator(new DefaultItemAnimator());
                            rvPetList.setAdapter(petListAdapter);
                        } else {
                            rvPetList.setVisibility(View.GONE);
                            ivNoPet.setVisibility(View.VISIBLE);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    rvPetList.setVisibility(View.GONE);
                    ivNoPet.setVisibility(View.VISIBLE);
                    ProjectUtils.showLong(mcontext, msg);
                }
            }
        });
    }

    public HashMap<String, String> getparm() {
        HashMap<String, String> parms = new HashMap<>();
        parms.put(Consts.USER_ID, loginDTO.getId());
        Log.e("parms", parms.toString());
        return parms;
    }


}