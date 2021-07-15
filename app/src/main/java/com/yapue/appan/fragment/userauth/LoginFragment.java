package com.yapue.appan.fragment.userauth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import com.google.gson.Gson;
import com.hbb20.CountryCodePicker;
//import com.mercadopago.android.px.core.MercadoPagoCheckout;
import com.mercadopago.android.px.core.MercadoPagoCheckout;
import com.mercadopago.android.px.model.Payment;
import com.mercadopago.android.px.model.exceptions.MercadoPagoError;
import com.yapue.appan.R;
import com.yapue.appan.activity.BaseActivity;
import com.yapue.appan.activity.UserProfile.UserProfileActivity;
import com.yapue.appan.activity.register.ForgotpasswordActivity;
import com.yapue.appan.activity.register.LoginSignupactivity;
import com.yapue.appan.https.HttpsRequest;
import com.yapue.appan.interfaces.Helper;
import com.yapue.appan.jsonparser.JSONParser;
import com.yapue.appan.models.LoginDTO;
import com.yapue.appan.network.NetworkManager;
import com.yapue.appan.sharedprefrence.SharedPrefrence;
import com.yapue.appan.utils.Consts;
import com.yapue.appan.utils.CustomButton;
import com.yapue.appan.utils.CustomEditText;
import com.yapue.appan.utils.CustomTextViewBold;
import com.yapue.appan.utils.ProjectUtils;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;
import static com.yapue.appan.MyFirebaseMessagingService.MyPREFERENCES;


public class LoginFragment extends Fragment implements View.OnClickListener {
    View view;
    private CustomButton CBlogin,test_payment;
    private CustomEditText CETemail, CETpassword, mobileET;
    CustomTextViewBold CTVforgot;
    LoginDTO loginDTO;
    SharedPrefrence sharedPrefrence;
    SharedPreferences firebaseToken;
    ImageView text_visible3;
    LinearLayout LLvisible;
    private boolean isHide = false;
    String strcountryCode;
    private SharedPreferences userDetails;
    // private TelephonyManager telephonyManager;
    HashMap<String, String> parms = new HashMap<>();
    CardView submitBTN;
    //private String otp;
    private String TAG = LoginFragment.class.getSimpleName();
    private CountryCodePicker countryCodePicker;
    LoginSignupactivity mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        sharedPrefrence = SharedPrefrence.getInstance(getActivity());
        firebaseToken = getActivity().getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        userDetails = getActivity().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        Log.e("tokensss", userDetails.getString(Consts.TOKAN, ""));
        //   telephonyManager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        //  Log.e("LOGIN_ID", "my id: " + telephonyManager.getDeviceId() + "  co" + telephonyManager.getSimCountryIso());
        init();
        return view;
    }

    public void init() {
        submitBTN = view.findViewById(R.id.submitBTN);
        submitBTN.setOnClickListener(this);

        mobileET = view.findViewById(R.id.mobileET);
        countryCodePicker = view.findViewById(R.id.ccp);

        CBlogin = (CustomButton) view.findViewById(R.id.CBlogin);
        test_payment = (CustomButton) view.findViewById(R.id.test_payment);
        text_visible3 = (ImageView) view.findViewById(R.id.text_visible3);
        LLvisible = (LinearLayout) view.findViewById(R.id.LLvisible);

        CETemail = (CustomEditText) view.findViewById(R.id.CETemail);
        CETpassword = (CustomEditText) view.findViewById(R.id.CETpassword);

        CTVforgot = (CustomTextViewBold) view.findViewById(R.id.CTVforgot);

        CBlogin.setOnClickListener(this);
        test_payment.setOnClickListener(this);
        CTVforgot.setOnClickListener(this);
        LLvisible.setOnClickListener(this);

    }

    public void payment()
    {
        new MercadoPagoCheckout.Builder("APP_USR-6e83bace-a65b-4d9c-8d8c-2e192743d016",
                "71139364-8ff573a3-9173-4ba6-bd8b-488ad73a6e84")
                .build()
                .startPayment(getActivity(), 1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.CBlogin:
                loginSubmit();
                break;
            case R.id.CTVforgot:
                startActivity(new Intent(getActivity(), ForgotpasswordActivity.class));
                break;

            case R.id.LLvisible:
                passwordShow();
                break;

            case R.id.submitBTN:
                submitForm();
                break;
            case R.id.test_payment:
                payment();
                break;

        }
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        Log.e(TAG, " payment call back --->");
        if (requestCode == 1) {
            if (resultCode == MercadoPagoCheckout.PAYMENT_RESULT_CODE) {
                final Payment payment = (Payment) data.getSerializableExtra(MercadoPagoCheckout.EXTRA_PAYMENT_RESULT);
                Log.e(TAG, " payment.getStatus() --->" + payment.getPaymentStatus());
                // ((TextView) findViewById(R.id.mp_results)).setText("Resultado del pago: " + );
                //Done!
            } else if (resultCode == 2) {
                if (data != null && data.getExtras() != null
                        && data.getExtras().containsKey(MercadoPagoCheckout.EXTRA_ERROR)) {
                    final MercadoPagoError mercadoPagoError =
                            (MercadoPagoError) data.getSerializableExtra(MercadoPagoCheckout.EXTRA_ERROR);
                    Log.e(TAG, " payment Error --->" +mercadoPagoError.getMessage());
                    //   ((TextView) findViewById(R.id.mp_results)).setText("Error: " +  mercadoPagoError.getMessage());
                    //Resolve error in checkout
                } else {
                    //Resolve canceled checkout
                }
            }
        }
    }

    private void passwordShow() {
        if (isHide) {
            text_visible3.setImageResource(R.drawable.visible_black);
            CETpassword.setTransformationMethod(null);
            isHide = false;
        } else {
            text_visible3.setImageResource(R.drawable.invisible_black);
            CETpassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            isHide = true;
        }

    }

    public void submitForm() {
        if (!validatePhone()) {
            return;
        } else {
            if (NetworkManager.isConnectToInternet(mContext)) {
//                Random otp1 = new Random();
//                StringBuilder builder = new StringBuilder();
//                for (int count = 0; count <= 3; count++) {
//                    builder.append(otp1.nextInt(10));
//                }
//                otp = builder.toString();
                loginCall();

            } else {
                ProjectUtils.showLong(mContext, getResources().getString(R.string.internet_concation));
            }
        }
    }

    public boolean validatePhone() {
        if (mobileET.getText().toString().trim().equalsIgnoreCase("")) {
            mobileET.setError("Please enter mobile number");
            mobileET.requestFocus();
            return false;
        } else {
            if (!ProjectUtils.isPasswordValid(mobileET.getText().toString().trim())) {
                mobileET.setError("Please enter valid mobile number");
                mobileET.requestFocus();
                return false;
            } else {
                mobileET.setError(null);
                mobileET.clearFocus();
                return true;
            }
        }
    }

    private void loginSubmit() {
        if (!validateEmail()) {
            return;
        } else if (!validatePassword()) {
            return;
        } else {
            if (NetworkManager.isConnectToInternet(getActivity())) {
                login();
            } else {
                ProjectUtils.showToast(getActivity(), getResources().getString(R.string.internet_concation));
            }
        }

    }

    public boolean validatePassword() {
        if (CETpassword.getText().toString().trim().equalsIgnoreCase("")) {
            CETpassword.setError(getString(R.string.password_val));
            CETpassword.requestFocus();
            return false;
        } else {
            if (!ProjectUtils.isPasswordValid(CETpassword.getText().toString().trim())) {
                CETpassword.setError(getString(R.string.password_val1));
                CETpassword.requestFocus();
                return false;
            } else {
                CETpassword.setError(null);
                CETpassword.clearFocus();
                return true;
            }
        }
    }

    public boolean validateEmail() {
        if (!ProjectUtils.isEmailValid(CETemail.getText().toString().trim())) {
            CETemail.setError("Please enter correct email.");
            CETemail.requestFocus();
            return false;
        } else {
            CETemail.setError(null);
            CETemail.clearFocus();
            return true;
        }
    }


    public void login() {
        ProjectUtils.showProgressDialog(getActivity(), true, getResources().getString(R.string.please_wait));
        AndroidNetworking.post(Consts.BASE_URL + Consts.LOGIN_NEW)
                .addBodyParameter(getParms())
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ProjectUtils.pauseProgressDialog();
                        JSONParser jsonParser = new JSONParser(getActivity(), response);

                        if (jsonParser.RESULT) {
                            try {
                                loginDTO = new Gson().fromJson(response.getJSONObject("data").toString(), LoginDTO.class);
                                sharedPrefrence.setParentUser(loginDTO, Consts.LOGINDTO);
                                sharedPrefrence.setBooleanValue(SharedPrefrence.IS_LOGIN, true);
                                sharedPrefrence.setValue(SharedPrefrence.USER_EMAIL, CETemail.getText().toString().trim());
                                sharedPrefrence.setValue(SharedPrefrence.PASSWORD, CETpassword.getText().toString().trim());
                                next();
                                ProjectUtils.showToast(getActivity(), jsonParser.MESSAGE);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        } else {
                            ProjectUtils.showToast(getActivity(), jsonParser.MESSAGE);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("Log", anError.getErrorBody());
                        ProjectUtils.pauseProgressDialog();
                    }
                });
    }

    public Map<String, String> getParms() {

        HashMap<String, String> params = new HashMap<>();
        params.put(Consts.EMAIL, CETemail.getText().toString().trim());
        params.put(Consts.PASSWORD, CETpassword.getText().toString().trim());
        params.put(Consts.DEVICE_TOKAN, userDetails.getString(Consts.TOKAN, ""));
        params.put(Consts.DEVICE_ID, "123456");
        params.put(Consts.OS_TYPE, "android");

        Log.e("Login", params.toString());
        return params;
    }

    public void next() {
        if (NetworkManager.isConnectToInternet(getActivity())) {
            if (!loginDTO.getAddress().equalsIgnoreCase("")) {
                ProjectUtils.showLong(getActivity(), "Login Successful");
                Intent intent = new Intent(getActivity(), BaseActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                getActivity().finish();
            } else {
                ProjectUtils.showLong(getActivity(), "Please Update your profile.");
                Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                intent.putExtra("FlagLogin", 1);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                getActivity().finish();

            }
        } else {
            ProjectUtils.showLong(getActivity(), "Please enable your internet connection.");
        }
    }


    public void loginCall() {
        //parms.put(Consts.MOBILE_NO, mobileET.getText().toString().trim());
        parms.put(Consts.EMAIL, CETemail.getText().toString().trim());
        parms.put(Consts.PASSWORD, CETpassword.getText().toString().trim());
        parms.put(Consts.DEVICE_ID, "12345");
        //parms.put(Consts.OTP, otp);
        // parms.put(Consts.COUNTRY_CODE, countryCodePicker.getSelectedCountryCode() + "");
        parms.put(Consts.OS_TYPE, "android");
        parms.put(Consts.DEVICE_TOKAN,""+firebaseToken.getString(Consts.TOKAN, ""));
        // sharedPrefrence.setValue(Consts.OTP, otp);

        ProjectUtils.showProgressDialog(mContext, true, "Please Wait!!");
        new HttpsRequest(Consts.LOGIN_NEW, parms, mContext).stringPost(TAG, new Helper() {
            @Override
            public void backResponse(boolean flag, String msg, JSONObject response) {
                if (flag) {
                    ProjectUtils.showLong(mContext, msg);

                    try {
                        LoginDTO loginDTO = new Gson().fromJson(response.getJSONObject("data").toString(), LoginDTO.class);
                        sharedPrefrence.setParentUser(loginDTO, Consts.LOGINDTO);

                        sharedPrefrence.setBooleanValue(SharedPrefrence.IS_LOGIN, true);
                        sharedPrefrence.setValue(SharedPrefrence.USER_EMAIL, CETemail.getText().toString().trim());
                        sharedPrefrence.setValue(SharedPrefrence.PASSWORD, CETpassword.getText().toString().trim());
                        Intent intent = new Intent(mContext, BaseActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        getActivity().finish();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    ProjectUtils.showLong(mContext, msg);
                }
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (LoginSignupactivity) context;
    }
}
