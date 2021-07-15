package com.yapue.appan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.yapue.appan.models.LoginDTO;
import com.yapue.appan.sharedprefrence.SharedPrefrence;
import com.yapue.appan.utils.Consts;

@SuppressLint("Registered")
public class NavigationDrawerSettings {
    public Activity activity;
    private LoginDTO loginDTO;

    public NavigationDrawerSettings(Activity parentActivityIntent, int nav_drawer) {
        this.activity = parentActivityIntent;
        // get the user data
        SharedPrefrence preference = SharedPrefrence.getInstance(activity.getApplicationContext());
        loginDTO = preference.getParentUser(Consts.LOGINDTO);
        String _User_First_Name = loginDTO.getFirst_name();
        String _User_Last_Name = loginDTO.getLast_name();
        String _User_Full_Name = _User_First_Name + _User_Last_Name;
        String _User_Email_Address = loginDTO.getEmail();
        String pic = loginDTO.getProfile_pic();
        Toast.makeText(activity.getApplicationContext(), pic, Toast.LENGTH_LONG).show();
        // get the nav drawer
        NavigationView navigationView = activity.findViewById(nav_drawer);
        navigationView.setVisibility(View.VISIBLE);
        // get the menus of nav drawer
        Menu nav_menus = navigationView.getMenu();
        nav_menus.findItem(R.id.nav_login).setTitle("Login"); //sample test
        // get the header of nav drawer
        View header_layout = (activity.getLayoutInflater()).inflate(R.layout.header_navigation_menus, activity.findViewById(R.id.nav_profile_view), false);
        ((TextView) header_layout.findViewById(R.id.nav_profile_name)).setText(_User_Full_Name); // sample
        ((TextView) header_layout.findViewById(R.id.nav_profile_email)).setText(_User_Email_Address); // sample
        //((ImageView) header_layout.findViewById(R.id.nav_profile_pic)).setImageBitmap(BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.developer)); // sample
        ((ImageView) header_layout.findViewById(R.id.nav_profile_pic)).setImageResource(R.drawable.developer); // sample
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
                Intent intent = new Intent(activity, activity.getClass());
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                activity.startActivity(intent);
            } else if (item.getItemId() == R.id.nav_ui_mode) {
                //new Settings(activity).VisualModeSettings();
            }
            return false;
        });
    }
}
