package com.yapue.appan.multipleimagepicker;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.yapue.appan.R;
import com.yapue.appan.https.HttpsRequest;
import com.yapue.appan.interfaces.Helper;
import com.yapue.appan.models.LoginDTO;
import com.yapue.appan.sharedprefrence.SharedPrefrence;
import com.yapue.appan.utils.Consts;
import com.yapue.appan.utils.ProjectUtils;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class MultiMyPetImageSelectorActivity extends AppCompatActivity implements MultiImageSelectorFragment.Callback {

    // Single choice
    public static final int MODE_SINGLE = 0;
    // Multi choice
    public static final int MODE_MULTI = 1;

    /**
     * Max image size，int，{@link #DEFAULT_IMAGE_SIZE} by default
     */
    public static final String EXTRA_SELECT_COUNT = "max_select_count";

    /**
     * Min image size，int，{@link #DEFAULT_IMAGE_SIZE} by default
     */
    public static final String EXTRA_SELECT_MIN_COUNT = "min_select_count";
    /**
     * Select mode，{@link #MODE_MULTI} by default
     */
    public static final String EXTRA_SELECT_MODE = "select_count_mode";
    /**
     * Whether show camera，true by default
     */
    public static final String EXTRA_SHOW_CAMERA = "show_camera";
    /**
     * Result data set，ArrayList&lt;String&gt;
     */
    public static final String EXTRA_RESULT = "select_result";
    /**
     * Original data set
     */
    public static final String EXTRA_DEFAULT_SELECTED_LIST = "default_list";
    // Default image size
    private static final int DEFAULT_IMAGE_SIZE = 9;
    // Default min image size
    private static final int DEFAULT_MIN_IMAGE_SIZE = 1;

    private ArrayList<String> resultList = new ArrayList<>();
    private Button mSubmitButton;
    private int mDefaultCount = DEFAULT_IMAGE_SIZE;
    private int mDefaultMinCount = DEFAULT_MIN_IMAGE_SIZE;

    HashMap<String, File> Imagelist;
    SharedPrefrence sharedPrefrence;
    LoginDTO loginDTO;
    String petId = "";
    private HashMap<String, String> values = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_default);
        sharedPrefrence = SharedPrefrence.getInstance(MultiMyPetImageSelectorActivity.this);
        loginDTO = sharedPrefrence.getParentUser(Consts.LOGINDTO);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLACK);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        final Intent intent = getIntent();
        mDefaultCount = intent.getIntExtra(EXTRA_SELECT_COUNT, DEFAULT_IMAGE_SIZE);
        mDefaultMinCount = intent.getIntExtra(EXTRA_SELECT_MIN_COUNT, DEFAULT_MIN_IMAGE_SIZE);
        petId = intent.getStringExtra(Consts.PET_ID);
        final int mode = intent.getIntExtra(EXTRA_SELECT_MODE, MODE_MULTI);
        final boolean isShow = intent.getBooleanExtra(EXTRA_SHOW_CAMERA, true);
        if (mode == MODE_MULTI && intent.hasExtra(EXTRA_DEFAULT_SELECTED_LIST)) {
            resultList = intent.getStringArrayListExtra(EXTRA_DEFAULT_SELECTED_LIST);
        }

        mSubmitButton = (Button) findViewById(R.id.commit);
        if (mode == MODE_MULTI) {
            updateDoneText(resultList);
            mSubmitButton.setVisibility(View.VISIBLE);
            mSubmitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Imagelist = new HashMap<>();
                    if (resultList != null && resultList.size() > 0) {
                        // Notify success
                        /*Intent data = new Intent();
                        data.putStringArrayListExtra(EXTRA_RESULT, resultList);
                        setResult(RESULT_OK, data);*/

                        for (int j = 0; j < resultList.size(); j++) {

                            String imagePath = ProjectUtils.compressImage(resultList.get(j), MultiMyPetImageSelectorActivity.this);

                            File file = new File(imagePath);
                            Imagelist.put("files[" + String.valueOf(j) + "]", file);

                        }
                        uploadimage();

                    } else {
                        setResult(RESULT_CANCELED);
                    }
                    //finish();
                }
            });
        } else {
            mSubmitButton.setVisibility(View.GONE);
        }

        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putInt(MultiImageSelectorFragment.EXTRA_SELECT_COUNT, mDefaultCount);
            bundle.putInt(MultiImageSelectorFragment.EXTRA_SELECT_MODE, mode);
            bundle.putBoolean(MultiImageSelectorFragment.EXTRA_SHOW_CAMERA, isShow);
            bundle.putStringArrayList(MultiImageSelectorFragment.EXTRA_DEFAULT_SELECTED_LIST, resultList);

            getSupportFragmentManager().beginTransaction().add(R.id.image_grid, Fragment.instantiate(this, MultiImageSelectorFragment.class.getName(), bundle)).commit();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(RESULT_CANCELED);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Update done button by select image data
     *
     * @param resultList selected image data
     */
    private void updateDoneText(ArrayList<String> resultList) {
        int size = 0;
        if (resultList == null || resultList.size() <= (mDefaultMinCount - 1)) {
            mSubmitButton.setText(R.string.action_done);
            mSubmitButton.setEnabled(false);
            size = resultList.size();
        } else {
            size = resultList.size();
            mSubmitButton.setEnabled(true);
        }
        mSubmitButton.setText(getString(R.string.action_button_string, getString(R.string.action_done), size, mDefaultCount));
    }

    @Override
    public void onSingleImageSelected(String path) {
        Intent data = new Intent();
        resultList.add(path);
        data.putStringArrayListExtra(EXTRA_RESULT, resultList);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onImageSelected(String path) {
        if (!resultList.contains(path)) {
            resultList.add(path);
        }
        updateDoneText(resultList);
    }

    @Override
    public void onImageUnselected(String path) {
        if (resultList.contains(path)) {
            resultList.remove(path);
        }
        updateDoneText(resultList);
    }

    @Override
    public void onCameraResultUpdate(String path, boolean isLimitExceed) {

        if (!isLimitExceed) {
            if (!resultList.contains(path)) {
                resultList.add(path);
            }
            updateDoneText(resultList);
        }

    }

    @Override
    public void onCameraShot(File imageFile) {
        if (imageFile != null) {
            // notify system the image has change
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(imageFile)));
            Intent data = new Intent();
            resultList.add(imageFile.getAbsolutePath());
            data.putStringArrayListExtra(EXTRA_RESULT, resultList);
            setResult(RESULT_OK, data);
            finish();
        }
    }

    public void uploadimage() {

        values.put(Consts.PET_ID, petId);
        values.put(Consts.USER_ID, loginDTO.getId());

        ProjectUtils.showProgressDialog(MultiMyPetImageSelectorActivity.this, true, "Please wait...");
        new HttpsRequest(Consts.ADD_PET_MEDIA, values, Imagelist, MultiMyPetImageSelectorActivity.this).imagePost("multiimages", new Helper() {
            @Override
            public void backResponse(boolean flag, String msg, JSONObject response) {
                if (flag) {
                    Intent data = new Intent();
                    data.putStringArrayListExtra(EXTRA_RESULT, resultList);
                    setResult(RESULT_OK, data);
                    ProjectUtils.showLong(MultiMyPetImageSelectorActivity.this, msg);
                    finish();

                } else {
                    ProjectUtils.showLong(MultiMyPetImageSelectorActivity.this, msg);
                }

            }
        });
    }

}
