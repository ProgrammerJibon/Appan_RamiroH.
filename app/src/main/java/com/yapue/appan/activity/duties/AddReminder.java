package com.yapue.appan.activity.duties;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.yapue.appan.R;
import com.yapue.appan.SysApplication;
import com.yapue.appan.adapter.ReminderAdapter;
import com.yapue.appan.models.LoginDTO;
import com.yapue.appan.models.ReminderCategory;
import com.yapue.appan.sharedprefrence.SharedPrefrence;
import com.yapue.appan.utils.Consts;
import com.yapue.appan.utils.ProjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by welcome pc on 26-12-2017.
 */

public class AddReminder extends AppCompatActivity implements View.OnClickListener {
    SysApplication sysApplication;
    SharedPrefrence share;
    ListView reminder_list;
    private ReminderAdapter adapter;
    List<ReminderCategory> listItems = new ArrayList<>();
    LinearLayout back;
    private Context mContext;
    public LoginDTO loginDTO;
    private String TAG = AddReminder.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProjectUtils.setStatusBarGradiant(AddReminder.this);
        setContentView(R.layout.add_reminder);
        mContext = AddReminder.this;

        sysApplication = SysApplication.getInstance();
        share = SharedPrefrence.getInstance(this);
        loginDTO = share.getParentUser(Consts.LOGINDTO);
        initView();
        loadCategory();
        adapter = new ReminderAdapter(this, listItems);
        reminder_list.setAdapter(adapter);
        reminder_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.startAnimation(AnimationUtils.loadAnimation(AddReminder.this, R.anim.click_event));
                Intent intent = new Intent(AddReminder.this, CreateReminder.class);
                intent.putExtra("reminderObj", listItems.get(position));
                startActivity(intent);
            }
        });
    }

    public void initView() {
        reminder_list = (ListView) findViewById(R.id.reminder_list);
        back = (LinearLayout) findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    public void loadCategory() {
        String[] listValue = getResources().getStringArray(R.array.reminder_category);
        for (int i = 0; i < listValue.length; i++) {
            try {
                ReminderCategory reminderCategory = new ReminderCategory();
                Object temp = sysApplication.reminderMapping.get(listValue[i]);
                if(temp != null) {
                    reminderCategory.setImgSource(sysApplication.reminderMapping.get(listValue[i]));
                    reminderCategory.setName(listValue[i]);
                    listItems.add(reminderCategory);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.click_event));
        switch (v.getId()) {
            case R.id.back: {
                finish();
                break;
            }
        }
    }
}
