package com.yapue.appan.activity.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yapue.appan.R;
import com.yapue.appan.activity.BaseActivity;
import com.yapue.appan.adapter.ChatListNotificationAdapter;
import com.yapue.appan.https.HttpsRequest;
import com.yapue.appan.interfaces.Helper;
import com.yapue.appan.models.ChatListDTO;
import com.yapue.appan.models.LoginDTO;
import com.yapue.appan.network.NetworkManager;
import com.yapue.appan.sharedprefrence.SharedPrefrence;
import com.yapue.appan.utils.Consts;
import com.yapue.appan.utils.CustomTextViewBold;
import com.yapue.appan.utils.ProjectUtils;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatNotificationActivity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private String TAG = ChatNotificationActivity.class.getSimpleName();
    private RecyclerView rvChatList;
    private ChatListNotificationAdapter chatListAdapter;
    private ArrayList<ChatListDTO> chatList;
    private LinearLayoutManager mLayoutManager;
    private SharedPrefrence prefrence;
    private LoginDTO loginDTO;
    private CustomTextViewBold tvNo;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Context mContext;
    private ImageView IVback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProjectUtils.setStatusBarGradiant(ChatNotificationActivity.this);
        setContentView(R.layout.activity_chat);
        mContext = ChatNotificationActivity.this;
        prefrence = SharedPrefrence.getInstance(mContext);
        loginDTO = prefrence.getParentUser(Consts.LOGINDTO);
        setUiAction();
    }

    public void setUiAction() {
        IVback = (ImageView) findViewById(R.id.IVback);
        IVback.setOnClickListener(this);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        tvNo = findViewById(R.id.tvNo);
        rvChatList = findViewById(R.id.rvChatList);

        mLayoutManager = new LinearLayoutManager(mContext.getApplicationContext());
        rvChatList.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.IVback:
                Intent intent = new Intent(mContext, BaseActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {

                                        Log.e("Runnable", "FIRST");
                                        if (NetworkManager.isConnectToInternet(mContext)) {
                                            swipeRefreshLayout.setRefreshing(true);
                                            getChat();

                                        } else {
                                            ProjectUtils.showLong(mContext, getResources().getString(R.string.internet_concation));
                                        }
                                    }
                                }
        );
    }

    public void getChat() {
        ProjectUtils.showProgressDialog(mContext, true, getResources().getString(R.string.please_wait));
        new HttpsRequest(Consts.GET_CHAT_HISTORY_API, getparm(), mContext).stringPost(TAG, new Helper() {
            @Override
            public void backResponse(boolean flag, String msg, JSONObject response) {
                ProjectUtils.pauseProgressDialog();
                swipeRefreshLayout.setRefreshing(false);
                if (flag) {
                    tvNo.setVisibility(View.GONE);
                    rvChatList.setVisibility(View.VISIBLE);
                    try {
                        chatList = new ArrayList<>();
                        Type getpetDTO = new TypeToken<List<ChatListDTO>>() {
                        }.getType();
                        chatList = (ArrayList<ChatListDTO>) new Gson().fromJson(response.getJSONArray("data").toString(), getpetDTO);
                        showData();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else {
                    ProjectUtils.showLong(mContext, msg);
                    tvNo.setVisibility(View.VISIBLE);
                    rvChatList.setVisibility(View.GONE);
                }
            }
        });
    }

    public HashMap<String, String> getparm() {
        HashMap<String, String> parms = new HashMap<>();
        parms.put(Consts.USER_ID, loginDTO.getId());
        return parms;
    }

    public void showData() {
        chatListAdapter = new ChatListNotificationAdapter(ChatNotificationActivity.this, chatList);
        rvChatList.setAdapter(chatListAdapter);
    }


    @Override
    public void onRefresh() {
        Log.e("ONREFREST_Firls", "FIRS");
        getChat();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(mContext, BaseActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
