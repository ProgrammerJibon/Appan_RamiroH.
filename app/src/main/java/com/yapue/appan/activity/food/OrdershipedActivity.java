package com.yapue.appan.activity.food;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.flutterwave.raveandroid.RaveConstants;
import com.flutterwave.raveandroid.RavePayActivity;
import com.flutterwave.raveandroid.RavePayManager;
import com.google.android.gms.location.places.Place;
import com.google.gson.Gson;
import com.hbb20.CountryCodePicker;
import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPGService;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;
import com.yapue.appan.R;
import com.yapue.appan.SysApplication;
import com.yapue.appan.activity.PaymentViewActivity;
import com.yapue.appan.https.HttpsRequest;
import com.yapue.appan.interfaces.Helper;
import com.yapue.appan.jsonparser.JSONParser;
import com.yapue.appan.models.AddressDTO;
import com.yapue.appan.models.CheckPromoCodeDTO;
import com.yapue.appan.models.LoginDTO;
import com.yapue.appan.models.MakeOrderDTO;
import com.yapue.appan.models.MercadoObject;
import com.yapue.appan.sharedprefrence.SharedPrefrence;
import com.yapue.appan.utils.Consts;
import com.yapue.appan.utils.CustomEditText;
import com.yapue.appan.utils.ProjectUtils;

import com.schibstedspain.leku.LocationPickerActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import static com.schibstedspain.leku.LocationPickerActivityKt.LATITUDE;
import static com.schibstedspain.leku.LocationPickerActivityKt.LONGITUDE;
import static com.schibstedspain.leku.LocationPickerActivityKt.ZIPCODE;

public class OrdershipedActivity extends AppCompatActivity implements OnClickListener {

    Context mContext;
    CustomEditText cetZipCode, cetName, cetMobileNo, cetAddressMap, cetAddress, cetSpecialNote, cetEmail, cetCity, cetCountry;
    RadioGroup RGpayment;
    RadioButton RBcaseon, RBonline;

    CardView cvOrder;
    LinearLayout back;
    private String TAG = OrdershipedActivity.class.getSimpleName();
    private SharedPrefrence prefrence;
    private LoginDTO loginDTO;
    private Place place;
    private MakeOrderDTO makeOrderDTO;
    private MercadoObject mercadoObject;
    HashMap<String, String> parmsOrder = new HashMap<>();
    HashMap<String, String> paymentParam = new HashMap<>();
    private CountryCodePicker countryCodePicker;
    String orderID = "";
    String totalPay = "", shoppingpay = "";
    Double latitude, longitude;
    AddressDTO addressDTO;
    int flag = 0;
   // String orderId;
    String checksumString = "";
    float finalPrice;
    String selectedRadioButton = "";
    CheckPromoCodeDTO checkPromoCodeDTO;
    String figure = "0", promo_code = "", promo_code_id = "0", promo_code_type = "0", final_price = "0", discount = "0";

    String txRef;
    String country = "US", currency = "USD";
    SysApplication sysApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProjectUtils.setStatusBarGradiant(OrdershipedActivity.this);
        setContentView(R.layout.activity_ordershiped);
        sysApplication = (SysApplication)getApplication();
        mContext = OrdershipedActivity.this;
        prefrence = SharedPrefrence.getInstance(mContext);
        loginDTO = prefrence.getParentUser(Consts.LOGINDTO);

        totalPay = getIntent().getStringExtra(Consts.PAYMENT_STATUS);
        shoppingpay = getIntent().getStringExtra(Consts.SHIPPING_COST);

        finalPrice = (Float.valueOf(totalPay) + Float.valueOf(shoppingpay));

        if (getIntent().hasExtra(Consts.DTO)) {
            checkPromoCodeDTO = (CheckPromoCodeDTO) getIntent().getSerializableExtra(Consts.DTO);
            if (checkPromoCodeDTO != null) {

                figure = checkPromoCodeDTO.getFigure();
                promo_code = checkPromoCodeDTO.getPromo_code();
                promo_code_id = checkPromoCodeDTO.getPromo_code_id();
                promo_code_type = checkPromoCodeDTO.getPromo_code_type();
                final_price = checkPromoCodeDTO.getFinal_price();
                discount = checkPromoCodeDTO.getDiscount();

            } else {
                figure = "0";
                promo_code = "";
                promo_code_id = "0";
                promo_code_type = "0";
                final_price = String.valueOf((Float.valueOf(totalPay) + Float.valueOf(shoppingpay)));
                discount = "0";
            }
        }else{
            finalPrice = (Float.valueOf(totalPay) + Float.valueOf(shoppingpay));
        }


        findUI();
        if (getIntent().hasExtra(Consts.FLAG)) {
            flag = getIntent().getIntExtra(Consts.FLAG, 0);
            if (flag == 1) {
                addressDTO = (AddressDTO) getIntent().getSerializableExtra(Consts.ADDRESS);
                showData();
            }
        }

    }

    private void showData() {
        cetAddressMap.setText(addressDTO.getLandMark());
        cetCountry.setText(addressDTO.getCountry());
        cetCity.setText(addressDTO.getCity());
        cetAddress.setText(addressDTO.getAddress());
        cetZipCode.setText(addressDTO.getZip());

    }

    private void findUI() {
        countryCodePicker = (CountryCodePicker) findViewById(R.id.ccp);
        cetName = findViewById(R.id.cetName);
        cetMobileNo = findViewById(R.id.cetMobileNo);
        cetAddressMap = findViewById(R.id.cetAddressMap);
        cetAddress = findViewById(R.id.cetAddress);
        RGpayment = findViewById(R.id.RGpayment);
        RBonline = findViewById(R.id.RBonline);
        RBcaseon = findViewById(R.id.RBcaseon);
        cvOrder = findViewById(R.id.cvOrder);
        RBcaseon = findViewById(R.id.RBcaseon);
        back = findViewById(R.id.back);
        cetSpecialNote = findViewById(R.id.cetNote);
        cetEmail = findViewById(R.id.cetEmail);
        cetCity = findViewById(R.id.cetCity);
        cetCountry = findViewById(R.id.cetCountry);
        cetZipCode = findViewById(R.id.cetZipCode);

        back.setOnClickListener(this);
        cvOrder.setOnClickListener(this);
        cetAddressMap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.cvOrder:
                Log.e(TAG, "onClick: " + RGpayment.getCheckedRadioButtonId());
                int selectedId = RGpayment.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                selectedRadioButton = radioButton.getText().toString();

                order();
                break;
            case R.id.cetAddressMap:
                ProjectUtils.showProgressDialog(mContext, true, "Please Wait!!");
                findPlace();
                break;
        }
    }

    private void order() {
        if (!validateName()) {
            return;
        } else if (!validateMobile()) {
            return;
        } else if (!validateEmail()) {
            return;
        } else if (!validateAddress(cetAddressMap)) {
            return;
        } else if (!validateAddress(cetAddress)) {
            return;
        } else if (!validateCity()) {
            return;
        } else if (!validateCountry()) {
            return;
        } else if (!validateZip()) {
            return;
        } else {
            if (selectedRadioButton.equalsIgnoreCase(getResources().getString(R.string.online_payment))) {

                makeOrder("", "0",true);

            } else if (selectedRadioButton.equalsIgnoreCase(getResources().getString(R.string.cod))) {
                Random otp1 = new Random();
                StringBuilder builder = new StringBuilder();
                for (int count = 0; count <= 10; count++) {
                    builder.append(otp1.nextInt(10));
                }
                orderID = builder.toString();
                makeOrder("", "0",false);
            }
        }
    }





    public boolean validateName() {
        if (!ProjectUtils.isEditTextFilled(cetName)) {
            cetName.setError("Please enter your name");
            cetName.requestFocus();
            return false;
        } else {
            cetName.setError(null);
            cetName.clearFocus();
            return true;
        }
    }

    public boolean validateZip() {
        if (!ProjectUtils.isEditTextFilled(cetZipCode)) {
            cetZipCode.setError("Please enter zip code");
            cetZipCode.requestFocus();
            return false;
        } else {
            cetZipCode.setError(null);
            cetZipCode.clearFocus();
            return true;
        }
    }

    public boolean validateCountry() {
        if (!ProjectUtils.isEditTextFilled(cetCountry)) {
            cetCountry.setError("Please enter your country");
            cetCountry.requestFocus();
            return false;
        } else {
            cetCountry.setError(null);
            cetCountry.clearFocus();
            return true;
        }
    }

    public boolean validateCity() {
        if (!ProjectUtils.isEditTextFilled(cetCity)) {
            cetCity.setError("Please enter your city");
            cetCity.requestFocus();
            return false;
        } else {
            cetCity.setError(null);
            cetCity.clearFocus();
            return true;
        }
    }

    public boolean validateMobile() {
        if (!ProjectUtils.isEditTextFilled(cetMobileNo)) {
            cetMobileNo.setError("Please enter your mobile.");
            cetMobileNo.requestFocus();
            return false;
        } else {
            cetMobileNo.setError(null);
            cetMobileNo.clearFocus();
            return true;
        }
    }

    public boolean validateEmail() {
        if (!ProjectUtils.isEmailValid(cetEmail.getText().toString().trim())) {
            cetEmail.setError("Please enter correct email.");
            cetEmail.requestFocus();
            return false;
        } else {
            cetEmail.setError(null);
            cetEmail.clearFocus();
            return true;
        }

    }

    public boolean validateAddress(CustomEditText editText) {
        if (!ProjectUtils.isEditTextFilled(editText)) {
            editText.setError("Please enter your Address.");
            editText.requestFocus();
            return false;
        } else {
            editText.setError(null);
            editText.clearFocus();
            return true;
        }
    }


    public void findPlace() {
        try {
            Intent locationPickerIntent = new LocationPickerActivity.Builder()
                    .withGooglePlacesEnabled()
                    //.withLocation(41.4036299, 2.1743558)
                    .build(mContext);

            startActivityForResult(locationPickerIntent, 101);
        } catch (Exception e) {
            // TODO: Handle the error.
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                try {
                    ProjectUtils.pauseProgressDialog();

                    latitude = data.getDoubleExtra(LATITUDE, 0.0);
                    longitude = data.getDoubleExtra(LONGITUDE, 0.0);
                    Log.d("LONGITUDE****", longitude.toString());

                    String postalcode = data.getStringExtra(ZIPCODE);

                    getAddress(latitude, longitude);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == RaveConstants.RAVE_REQUEST_CODE && data != null) {
            Log.e(TAG, "onActivityResult: " + data);
            String message = data.getStringExtra("response");

            if (resultCode == RavePayActivity.RESULT_SUCCESS) {
                Log.e("RESULT_SUCCESS", data.getStringExtra("response"));

                makeOrder(txRef, "1",false);
            } else if (resultCode == RavePayActivity.RESULT_ERROR) {
                Log.e("RESULT_ERROR", data.toString());
            } else if (resultCode == RavePayActivity.RESULT_CANCELLED) {
                Log.e("RESULT_CANCELLED", data.toString());
            }
        }

    }

    public void getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            String add = obj.getAddressLine(0);
            add = add + "\n" + obj.getCountryName();
            add = add + "\n" + obj.getCountryCode();
            add = add + "\n" + obj.getAdminArea();
            add = add + "\n" + obj.getPostalCode();
            add = add + "\n" + obj.getSubAdminArea();
            add = add + "\n" + obj.getLocality();
            add = add + "\n" + obj.getSubThoroughfare();
            Log.e("IGA", "Address" + add);
            // Toast.makeText(this, "Address=>" + add,
            // Toast.LENGTH_SHORT).show();

            // TennisAppActivity.showDialog(add);

            cetAddressMap.setText(obj.getAddressLine(0));

            String areacode = obj.getPostalCode();
            String city = obj.getSubAdminArea();
            String state = obj.getAdminArea();
            String country = obj.getCountryName();

         /*   if (city != null) {
                cetCity.setText(city);
            }
            if (country != null) {
                cetCountry.setText("india");
            }*/
            if (areacode != null) {
                cetZipCode.setText(areacode);
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void makeOrder(String paymentId, final String status,boolean isPreOrder) {
        orderID = String.valueOf((long) Math.floor(Math.random() * 9000000000L));
        String payment_mode ="";
        parmsOrder.put(Consts.USER_ID, loginDTO.getId());
        parmsOrder.put(Consts.ADDRESS, cetAddressMap.getText().toString());
        parmsOrder.put(Consts.PAYMENT_ID, paymentId);
        parmsOrder.put(Consts.LANDMARK, cetAddress.getText().toString());
        parmsOrder.put(Consts.NAME, cetName.getText().toString());
        parmsOrder.put(Consts.COUNTRY_CODE, countryCodePicker.getSelectedCountryCode() + "");
        parmsOrder.put(Consts.MOBILE_NO, cetMobileNo.getText().toString());
        parmsOrder.put(Consts.EMAIL, cetEmail.getText().toString());
        parmsOrder.put(Consts.SPECIAL_NOTE, cetSpecialNote.getText().toString());
        parmsOrder.put(Consts.CITY, cetCity.getText().toString());
        parmsOrder.put(Consts.COUNTRY, cetCountry.getText().toString());
        parmsOrder.put(Consts.ZIP, cetZipCode.getText().toString().trim());
        parmsOrder.put(Consts.SHIPPING_COST, shoppingpay);
        parmsOrder.put(Consts.PAYMENT_STATUS, status);
        parmsOrder.put(Consts.ORDER_ID, orderID);

        if (selectedRadioButton.equalsIgnoreCase(getResources().getString(R.string.online_payment))) {
            payment_mode = "0";
            parmsOrder.put(Consts.PAYMENT_MODE, payment_mode);
        } else if (selectedRadioButton.equalsIgnoreCase(getResources().getString(R.string.cod))) {
            payment_mode = "1";
            parmsOrder.put(Consts.PAYMENT_MODE, payment_mode);
        }

        parmsOrder.put(Consts.PROMO_CODE_ID, promo_code_id);
        parmsOrder.put(Consts.PROMO_CODE_TYPE, promo_code_type);
        parmsOrder.put(Consts.FIGURE, figure);
        parmsOrder.put(Consts.PROMO_CODE, promo_code);
        parmsOrder.put(Consts.FINAL_PRICE, final_price);
        parmsOrder.put(Consts.DISCOUNT, discount);

        String finalPayment_mode = payment_mode;

        Log.e(TAG, "makeOrder: "+parmsOrder.toString());

        ProjectUtils.showProgressDialog(mContext, true, "Please Wait!!");
        new HttpsRequest(Consts.ORDER, parmsOrder, mContext).stringPost(TAG, new Helper() {
            @Override
            public void backResponse(boolean flag, String msg, JSONObject response) {
                if (flag) {
                    try {
                        try {
                            makeOrderDTO = new Gson().fromJson(response.getJSONObject("data").toString(), MakeOrderDTO.class);
                            generatePaymentURL(makeOrderDTO.getOrder_id());
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        if(isPreOrder)
                        {
                           // ProjectUtils.showLong(mContext, "Order placed");
                        }
                        else
                        {
                            if (status.equals("1")) {
                                Intent intent = new Intent(OrdershipedActivity.this, ViewOrderDetails.class);
                                intent.putExtra(Consts.MAKE_ORDER, makeOrderDTO);
                                startActivity(intent);
                                finish();
                            }else if(status.equalsIgnoreCase("0") && finalPayment_mode.equalsIgnoreCase("1")){
                                Intent intent = new Intent(OrdershipedActivity.this, ViewOrderDetails.class);
                                intent.putExtra(Consts.MAKE_ORDER, makeOrderDTO);
                                startActivity(intent);
                                finish();
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else {
                    ProjectUtils.showLong(mContext, msg);

                }
            }
        });
    }


    public void generatePaymentURL(String OrderId) {
        //test details token
        //TEST-4590669393882985-011016-bbf96318b199e06a2fd6a841d8785cd1-71139364
        // test public key
        //TEST-7ed95661-5bcf-4186-8fb2-caf3d6ecdb6a




        try {


            orderID = String.valueOf((long) Math.floor(Math.random() * 9000000000L));
            JSONObject paymentJSON = new JSONObject();
            //--------------------------------------------------------
            JSONArray itemsArray = new JSONArray();

            for (int i = 0; i < sysApplication.cartDTOList.size(); i++) {
                JSONObject items = new JSONObject();
                try {
                    items.putOpt("id", sysApplication.cartDTOList.get(i).getProduct_id());
                    items.putOpt("title", sysApplication.cartDTOList.get(i).getP_name());
                    items.putOpt("currency_id", "ARS");
                    items.putOpt("picture_url", "https://www.mercadopago.com/org-img/MP3/home/logomp3.gif");
                    items.putOpt("description", sysApplication.cartDTOList.get(i).getP_description());
                    items.putOpt("category_id", sysApplication.cartDTOList.get(i).getCategory_name());
                    items.putOpt("quantity", ProjectUtils.getIntValue(sysApplication.cartDTOList.get(i).getQuantity()));
                    items.putOpt("unit_price", sysApplication.cartDTOList.get(i).getPrice_dicount());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                itemsArray.put(items);
            }
            paymentJSON.putOpt("items", itemsArray);

            //--------------------------------------------------------
            JSONObject payer = new JSONObject();
            payer.putOpt("name", cetName.getText().toString());
            payer.putOpt("surname", cetName.getText().toString());
            payer.putOpt("email", cetEmail.getText().toString());

            JSONObject phone = new JSONObject();
            phone.putOpt("area_code", countryCodePicker.getSelectedCountryCode());
            phone.putOpt("number",cetMobileNo.getText().toString());
            payer.put("phone",phone);

            JSONObject address = new JSONObject();
            address.putOpt("street_name", cetAddressMap.getText().toString());
            address.putOpt("street_number", cetAddress.getText().toString());
            address.putOpt("city", cetCity.getText().toString());
            address.putOpt("zip_code",cetZipCode.getText().toString().trim());
            payer.put("address",address);

            paymentJSON.putOpt("payer", payer);
            //--------------------------------------------------------

            JSONObject back_urls = new JSONObject();
            back_urls.putOpt("success", Consts.MERCADO_SUCCESS_URL+OrderId);
            back_urls.putOpt("failure",Consts.MERCADO_FAIL_URL);
            back_urls.putOpt("pending", Consts.MERCADO_FAIL_URL);
            paymentJSON.putOpt("back_urls", back_urls);
            //--------------------------------------------------------

            paymentJSON.putOpt("auto_return", "approved");

            //--------------------------------------------------------
            JSONArray excluded_payment_methods = new JSONArray();
            enablePaymentMethod(excluded_payment_methods);

            JSONArray excluded_payment_types = new JSONArray();
            JSONObject excluded_payment_types_vale = new JSONObject();
            excluded_payment_types_vale.putOpt("id", "ticket");
            excluded_payment_types.put(excluded_payment_types_vale);


            JSONObject payment_methods = new JSONObject();
//            payment_methods.putOpt("excluded_payment_methods", excluded_payment_methods);
//            payment_methods.putOpt("excluded_payment_types", excluded_payment_types);
            payment_methods.putOpt("installments", 1);

            paymentJSON.putOpt("payment_methods", payment_methods);
            //--------------------------------------------------------

            //--------------------------------------------------------
            //paymentJSON.putOpt("notification_url", Consts.MERCADO_NOTIFY_URL+orderID);
            paymentJSON.putOpt("notification_url", Consts.MERCADO_NOTIFY_URL+OrderId);
            paymentJSON.putOpt("statement_descriptor", "MYBUSINESS");
            paymentJSON.putOpt("external_reference", "Reference_number");
            paymentJSON.putOpt("expires", true);
            paymentJSON.putOpt("expiration_date_from", "2021-04-01T12:00:00.000-04:00");
            paymentJSON.putOpt("expiration_date_to", "2023-12-31T12:00:00.000-04:00");
            //--------------------------------------------------------


            ProjectUtils.showProgressDialog(mContext, true, "Please Wait!!");
            new HttpsRequest(Consts.MERCADO_PREFERENCE_ID_API, paymentJSON, mContext).mercadoPaymentCall(TAG, new Helper() {
                @Override
                public void backResponse(boolean flag, String msg, JSONObject response) {

                    try {
                     //   ProjectUtils.showLong(mContext, "link generated sir");
                        mercadoObject = new Gson().fromJson(response.toString(), MercadoObject.class);
                        Log.e(TAG, "mercadoObject: --->"+mercadoObject.getInit_point());

                        payment(mercadoObject.getInit_point());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void payment(String URL) {

        Intent intent = new Intent(OrdershipedActivity.this, PaymentViewActivity.class);
        intent.putExtra(Consts.PAYAMENT_URL, URL);
        intent.putExtra(Consts.ORDER_ID, orderID);
        startActivity(intent);
        finish();
    }



    public void enablePaymentMethod(JSONArray excluded_payment_methods)
    {
        try {
            JSONObject method1 = new JSONObject();
            method1.put("id","master");
            excluded_payment_methods.put(method1);

            JSONObject method2 = new JSONObject();
            method2.put("id","visa");
            excluded_payment_methods.put(method2);

            JSONObject method3 = new JSONObject();
            method3.put("id","amex");
            excluded_payment_methods.put(method3);

            JSONObject method4 = new JSONObject();
            method4.put("id","diners");
            excluded_payment_methods.put(method4);

            JSONObject method5 = new JSONObject();
            method5.put("id","naranja");
            excluded_payment_methods.put(method5);

            JSONObject method6 = new JSONObject();
            method6.put("id","nativa");
            excluded_payment_methods.put(method6);

            JSONObject method7 = new JSONObject();
            method7.put("id","shopping");
            excluded_payment_methods.put(method7);

            JSONObject method8 = new JSONObject();
            method8.put("id","cencosud");
            excluded_payment_methods.put(method8);

            JSONObject method9 = new JSONObject();
            method9.put("id","cmr_master");
            excluded_payment_methods.put(method9);

            JSONObject method10 = new JSONObject();
            method10.put("id","argencard");
            excluded_payment_methods.put(method10);

            JSONObject method11 = new JSONObject();
            method11.put("id","cordial");
            excluded_payment_methods.put(method11);

            JSONObject method12 = new JSONObject();
            method12.put("id","cordobesa");
            excluded_payment_methods.put(method12);

            JSONObject method13 = new JSONObject();
            method13.put("id","cabal");
            excluded_payment_methods.put(method13);

            JSONObject method14 = new JSONObject();
            method14.put("id","debvisa");
            excluded_payment_methods.put(method14);

            JSONObject method15 = new JSONObject();
            method15.put("id","debmaster");
            excluded_payment_methods.put(method15);

            JSONObject method16 = new JSONObject();
            method16.put("id","maestro");
            excluded_payment_methods.put(method16);

            JSONObject method17 = new JSONObject();
            method17.put("id","debcabal");
            excluded_payment_methods.put(method17);

            JSONObject method18 = new JSONObject();
            method18.put("id","pagofacil");
            excluded_payment_methods.put(method18);

            JSONObject method19 = new JSONObject();
            method19.put("id","rapipago");
            excluded_payment_methods.put(method19);

            JSONObject method20 = new JSONObject();
            method20.put("id","bapropagos");
            excluded_payment_methods.put(method20);

            JSONObject method21 = new JSONObject();
            method21.put("id","cargavirtual");
            excluded_payment_methods.put(method21);

            JSONObject method22 = new JSONObject();
            method22.put("id","cobroexpress");
            excluded_payment_methods.put(method22);

            JSONObject method23 = new JSONObject();
            method23.put("id","redlink");
            excluded_payment_methods.put(method23);


        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }


    public void transactionStatus(String orderId) {
        new HttpsRequest(Consts.PAYTM_TRANS_STATUS_URL + orderId, mContext).stringGet(TAG, new Helper() {
            @Override
            public void backResponse(boolean flag, String msg, JSONObject response) {
                if (flag) {
                    Log.e(TAG, "Response :-------" + response.toString());
                }
            }
        });
    }




}
