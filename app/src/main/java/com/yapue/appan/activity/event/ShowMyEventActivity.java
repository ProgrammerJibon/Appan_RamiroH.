package com.yapue.appan.activity.event;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yapue.appan.NavigationDrawerSettings;
import com.yapue.appan.R;
import com.yapue.appan.adapter.EventListAdapter;
import com.yapue.appan.fragment.event.MyEventFragment;
import com.yapue.appan.fragment.event.OtherEventFragment;
import com.yapue.appan.models.EventDTO;
import com.yapue.appan.models.LoginDTO;
import com.yapue.appan.sharedprefrence.SharedPrefrence;
import com.yapue.appan.utils.Consts;
import com.yapue.appan.utils.CustomTextViewBold;
import com.yapue.appan.utils.ProjectUtils;

import java.util.ArrayList;

public class ShowMyEventActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout back;
    Context mContext;
    ArrayList<EventDTO> eventDTOlist;
    private SharedPrefrence sharedPrefrence;
    private LoginDTO loginDTO;
    EventListAdapter eventListAdapter;
    FloatingActionButton fabAddEvent;
    public MyEventFragment myEventFragment = new MyEventFragment();
    public OtherEventFragment otherEventFragment = new OtherEventFragment();
    public FragmentManager fm;

    CustomTextViewBold CTVBmyevent, CTVBotherevent;
    View viewOtherEvent, viewMyEvent;

    LinearLayout LLmyevent, LLOtherevent;

    public static String TAG_MYEVENT = "myevent";
    public static String TAG_OTHEREVENT = "otherevent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProjectUtils.setStatusBarGradiant(ShowMyEventActivity.this);
        setContentView(R.layout.activity_show_my_event);
        new NavigationDrawerSettings(this, R.id.nav_drawer_activity_my_event);
        mContext = ShowMyEventActivity.this;
        sharedPrefrence = SharedPrefrence.getInstance(mContext);
        loginDTO = sharedPrefrence.getParentUser(Consts.LOGINDTO);
        findUI();
    }

    private void findUI() {
        fm = getSupportFragmentManager();
        back = findViewById(R.id.back);
        LLOtherevent = findViewById(R.id.LLOtherevent);
        LLmyevent = findViewById(R.id.LLmyevent);
        fabAddEvent = findViewById(R.id.fabAddEvent);
        CTVBmyevent = findViewById(R.id.CTVBmyevent);
        CTVBotherevent = findViewById(R.id.CTVBotherevent);
        viewMyEvent = findViewById(R.id.viewMyEvent);
        viewOtherEvent = findViewById(R.id.viewOtherEvent);

        back.setOnClickListener(this);
        fabAddEvent.setOnClickListener(this);
        LLOtherevent.setOnClickListener(this);
        LLmyevent.setOnClickListener(this);
        myEvent();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabAddEvent:
                startActivity(new Intent(mContext, EventCreate.class));
                break;
            case R.id.back:
                finish();
                break;
            case R.id.LLOtherevent:
                myOtherEvent();
                break;
            case R.id.LLmyevent:
                myEvent();
                break;
        }
    }

    public void myEvent() {

        CTVBmyevent.setTextColor(getResources().getColor(R.color.colorPrimary));
        CTVBotherevent.setTextColor(getResources().getColor(R.color.gray));

        viewMyEvent.setVisibility(View.VISIBLE);
        viewOtherEvent.setVisibility(View.INVISIBLE);

        Fragment fragment = myEventFragment;
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frameevnt, fragment, TAG_MYEVENT);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void myOtherEvent() {

        CTVBmyevent.setTextColor(getResources().getColor(R.color.gray));
        CTVBotherevent.setTextColor(getResources().getColor(R.color.colorPrimary));
        viewMyEvent.setVisibility(View.INVISIBLE);
        viewOtherEvent.setVisibility(View.VISIBLE);

        Fragment fragment = otherEventFragment;
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frameevnt, fragment, TAG_OTHEREVENT);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
