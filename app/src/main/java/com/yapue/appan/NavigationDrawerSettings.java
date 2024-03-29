package com.yapue.appan;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.yapue.appan.activity.AboutUsActivity;
import com.yapue.appan.activity.BaseActivity;
import com.yapue.appan.activity.MyOrder.MyOrderActivity;
import com.yapue.appan.activity.UserProfile.UserProfileActivity;
import com.yapue.appan.activity.chat.ChatActivity;
import com.yapue.appan.activity.event.ShowMyEventActivity;
import com.yapue.appan.activity.notification.NotificationActivity;
import com.yapue.appan.activity.register.LoginSignupactivity;
import com.yapue.appan.fragment.NearBy.ShopActivity;
import com.yapue.appan.models.LoginDTO;
import com.yapue.appan.sharedprefrence.SharedPrefrence;
import com.yapue.appan.utils.Consts;

import java.io.File;

public class NavigationDrawerSettings {
    public Activity activity;
    public LoginDTO loginDTO;

    public NavigationDrawerSettings(Activity parentActivityIntent, int nav_drawer) {
        this.activity = parentActivityIntent;
        // get the user data
        SharedPrefrence preference = SharedPrefrence.getInstance(activity.getApplicationContext());
        loginDTO = preference.getParentUser(Consts.LOGINDTO);
        String _User_First_Name = loginDTO.getFirst_name();
        String _User_Last_Name = loginDTO.getLast_name();
        String _User_Full_Name = _User_First_Name + _User_Last_Name;
        String _User_Email_Address = loginDTO.getEmail();
        if (_User_Email_Address.equals("")) {
            _User_Email_Address = "username@domain.com";
        }

        // get the nav drawer
        NavigationView navigationView = activity.findViewById(nav_drawer);
        navigationView.setVisibility(View.VISIBLE);
        // get the menus of nav drawer
        Menu nav_menus = navigationView.getMenu();
        if (!loginDTO.getId().contains(Consts.GUEST_ID)) {
            (nav_menus.findItem(R.id.nav_login)).setVisible(false);
        }
        navigationView.setBackgroundColor(activity.getResources().getColor(R.color.Mod));

        // get the header of nav drawer
        View header_layout = (activity.getLayoutInflater()).inflate(R.layout.header_navigation_menus, activity.findViewById(R.id.nav_profile_view), false);
        File profile_pic_from_storage = new File(Environment.getExternalStorageDirectory(), ".ProgrammerJibon");
        profile_pic_from_storage = new File(String.valueOf(profile_pic_from_storage.getAbsoluteFile()), "profile.png");
        if (loggedIn(activity)) {
            if (profile_pic_from_storage.exists()) {
                ((ImageView) header_layout.findViewById(R.id.nav_profile_pic)).setImageBitmap(BitmapFactory.decodeFile(profile_pic_from_storage.getAbsolutePath()));
            }
        } else {
            ((ImageView) header_layout.findViewById(R.id.nav_profile_pic)).setImageResource(R.drawable.ic_outline_account_circle_24);
            header_layout.findViewById(R.id.nav_profile_pic).setBackgroundColor(activity.getResources().getColor(R.color.white));
        }
        header_layout.findViewById(R.id.nav_profile_pic).setOnClickListener(v -> {
            if (loggedIn(activity)) {
                new_activity(activity, UserProfileActivity.class);
            }
        });
        ((TextView) header_layout.findViewById(R.id.nav_profile_name)).setText(_User_Full_Name); // sample
        ((TextView) header_layout.findViewById(R.id.nav_profile_email)).setText(_User_Email_Address); // sample
        navigationView.addHeaderView(header_layout);

        // any item clicked of nav drawer
        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_exit) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage("Are you sure to exit?")
                        .setCancelable(false)
                        .setPositiveButton("Exit", (dialog, which) -> {
                            activity.finish();
                            activity.onBackPressed();
                        })
                        .setNegativeButton("Later", (dialog, which) -> {
                            dialog.cancel();
                        });
                builder.create().show();
            } else if (item.getItemId() == R.id.nav_home) {
                new_activity(activity, BaseActivity.class);
            } else if (item.getItemId() == R.id.nav_profile) {
                if (loggedIn(activity)) {
                    new_activity(activity, UserProfileActivity.class);
                }
            } else if (item.getItemId() == R.id.nav_messages) {
                if (loggedIn(activity)) {
                    new_activity(activity, ChatActivity.class);
                }
            } else if (item.getItemId() == R.id.nav_donate) {
                if (loggedIn(activity)) {
                    new_activity(activity, DonateActivity.class);
                }
            } else if (item.getItemId() == R.id.nav_myOrders) {
                if (loggedIn(activity)) {
                    new_activity(activity, MyOrderActivity.class);
                }
            } else if (item.getItemId() == R.id.nav_event) {
                if (loggedIn(activity)) {
                    new_activity(activity, ShowMyEventActivity.class);
                }
            } else if (item.getItemId() == R.id.nav_notification) {
                if (loggedIn(activity)) {
                    new_activity(activity, NotificationActivity.class);
                }
            } else if (item.getItemId() == R.id.nav_categories) {
                // categories intent opener
            } else if (item.getItemId() == R.id.nav_shops) {
                new_activity(activity, ShopActivity.class);
            } /*else if (item.getItemId() == R.id.nav_share) {
                new_activity(activity, ShopActivity.class);
                // this need to hire again for adding as new feature
            }else if (item.getItemId() == R.id.nav_ui_mode) {
                // nav ui mode between dark and light theme
                // this need to hire again for adding as new feature
            } else if (item.getItemId() == R.id.nav_settings) {
                // this need to hire again for adding as new feature
            }*/ else if (item.getItemId() == R.id.nav_about) {
                new_activity(activity, AboutUsActivity.class);
            } else if (item.getItemId() == R.id.nav_login) {
                loggedIn(activity);
            } else {
                Toast.makeText(activity.getApplicationContext(), "Please wait for next update", Toast.LENGTH_LONG).show();
            }
            ((DrawerLayout) activity.findViewById(R.id.MainActivity)).closeDrawers();
            return true;
        });
    }

    public boolean loggedIn(Activity activity2) {
        SharedPrefrence preference = SharedPrefrence.getInstance(activity.getApplicationContext());
        LoginDTO loginDTO = preference.getParentUser(Consts.LOGINDTO);
        if (loginDTO.getId().contains(Consts.GUEST_ID)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage("Please login to get all user access...")
                    .setCancelable(false)
                    .setPositiveButton("Login", (dialog, which) -> {
                        new_activity(activity2, LoginSignupactivity.class);
                    })
                    .setNegativeButton("Later", (dialog, which) -> {
                        dialog.cancel();
                    });
            builder.create().show();
            return false;
        }
        return true;
    }

    public boolean new_activity(Activity activity1, Class newClass) {
        try {
            Intent intentBrush = new Intent(activity1, newClass);
            intentBrush.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            intentBrush.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            intentBrush.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            activity1.startActivity(intentBrush);
            return true;
        } catch (Exception error) {
            Toast.makeText(activity1.getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            return false;
        }


    }
}
