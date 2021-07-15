package com.yapue.appan.fragment.NearBy;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yapue.appan.R;
import com.yapue.appan.adapter.UserListAdapter;
import com.yapue.appan.https.HttpsRequest;
import com.yapue.appan.interfaces.Helper;
import com.yapue.appan.models.LoginDTO;
import com.yapue.appan.models.NearByUserDTO;
import com.yapue.appan.sharedprefrence.SharedPrefrence;
import com.yapue.appan.utils.Consts;
import com.yapue.appan.utils.CustomEditText;
import com.yapue.appan.utils.GPSTracker;
import com.yapue.appan.utils.ProjectUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class UserListFrag extends Fragment {
    private View view;
    private String TAG = UserListFrag.class.getSimpleName();
    private ListView lvUserList;
    private ArrayList<NearByUserDTO> nearByUserDTOList;
    private SharedPrefrence share;
    private LoginDTO loginDTO;
    private HashMap<String, String> paramsUser = new HashMap<>();
    private LayoutInflater myInflater;
    public GPSTracker gps;
    double latitude, longitude;
    private CustomEditText etSearch;
    private UserListAdapter userListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_list, container, false);
        gps = new GPSTracker(getActivity());

        myInflater = LayoutInflater.from(getActivity());
        share = SharedPrefrence.getInstance(getActivity());
        loginDTO = share.getParentUser(Consts.LOGINDTO);
        if (gps.canGetLocation()) {

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();


            Log.e("Loction", "Lat" + latitude + "long" + longitude);
        } else {

            gps.showSettingsAlert();
        }

        paramsUser.put(Consts.USER_ID, loginDTO.getId());
        paramsUser.put(Consts.LATI, String.valueOf(latitude));
        paramsUser.put(Consts.LONGI, String.valueOf(longitude));
        lvUserList = (ListView) view.findViewById(R.id.lvUserList);
        etSearch = (CustomEditText) view.findViewById(R.id.etSearch);


        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userListAdapter.filter(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        getNearByUser();

        return view;

    }

    public void getNearByUser() {
        ProjectUtils.showProgressDialog(getActivity(), true, "Please wait....");
        new HttpsRequest(Consts.GET_NEAR_BY_USER, paramsUser, getActivity()).stringPost(TAG, new Helper() {
            @Override
            public void backResponse(boolean flag, String msg, JSONObject response) {
                if (flag) {
                    //  ProjectUtils.showLong(getActivity(), msg);
                    Type listType = new TypeToken<List<NearByUserDTO>>() {
                    }.getType();
                    try {
                        nearByUserDTOList = new ArrayList<>();
                        nearByUserDTOList = (ArrayList<NearByUserDTO>) new Gson().fromJson(response.getJSONArray("data").toString(), listType);
                        userListAdapter = new UserListAdapter(getActivity(), nearByUserDTOList, myInflater);
                        lvUserList.setAdapter(userListAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } else {
                    ProjectUtils.showLong(getActivity(), msg);
                }
            }
        });

    }


}
