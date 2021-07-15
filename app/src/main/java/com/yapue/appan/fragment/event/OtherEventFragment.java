package com.yapue.appan.fragment.event;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yapue.appan.R;
import com.yapue.appan.adapter.OtherEventListAdapter;
import com.yapue.appan.https.HttpsRequest;
import com.yapue.appan.interfaces.Helper;
import com.yapue.appan.models.LoginDTO;
import com.yapue.appan.models.OtherEventDTO;
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

public class OtherEventFragment extends Fragment {

    View view;
    RecyclerView rvEvent;
    private String TAG = OtherEventFragment.class.getSimpleName();
    ArrayList<OtherEventDTO> otherEventDTOList;
    private SharedPrefrence sharedPrefrence;
    private LoginDTO loginDTO;
    OtherEventListAdapter otherEventListAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_my_event, container, false);
        sharedPrefrence = SharedPrefrence.getInstance(getActivity());
        loginDTO = sharedPrefrence.getParentUser(Consts.LOGINDTO);
        findUI();
        return view;
    }

    private void findUI() {
        rvEvent = view.findViewById(R.id.rvEvent);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (NetworkManager.isConnectToInternet(getActivity())) {
            getOtherEvent();
        } else {
            ProjectUtils.showToast(getActivity(), getString(R.string.internet_concation));
        }
    }

    public void getOtherEvent() {
        new HttpsRequest(Consts.GETALLEVENT, getparm(), getActivity()).stringPost(TAG, new Helper() {
            @Override
            public void backResponse(boolean flag, String msg, JSONObject response) {
                if (flag) {
                    Type listType = new TypeToken<List<OtherEventDTO>>() {
                    }.getType();
                    try {
                        otherEventDTOList = new ArrayList<>();
                        otherEventDTOList = (ArrayList<OtherEventDTO>) new Gson().fromJson(response.getJSONArray("data").toString(), listType);

                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                        rvEvent.setLayoutManager(mLayoutManager);
                        rvEvent.setItemAnimator(new DefaultItemAnimator());

                        otherEventListAdapter = new OtherEventListAdapter(getActivity(), otherEventDTOList);
                        rvEvent.setAdapter(otherEventListAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    ProjectUtils.showLong(getActivity(), msg);
                }
            }
        });

    }

    public HashMap<String, String> getparm() {
        HashMap<String, String> parms = new HashMap<>();
        parms.put(Consts.USER_ID, loginDTO.getId());

        return parms;
    }

}
