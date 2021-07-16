package com.yapue.appan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yapue.appan.activity.PaymentViewActivity;
import com.yapue.appan.models.LoginDTO;
import com.yapue.appan.sharedprefrence.SharedPrefrence;
import com.yapue.appan.utils.Consts;

import java.util.ArrayList;

public class DonateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        SharedPrefrence preference = SharedPrefrence.getInstance(getApplicationContext());
        LoginDTO loginDTO = preference.getParentUser(Consts.LOGINDTO);
        ListView listView = findViewById(R.id.idOfTakas);
        EditText editText = findViewById(R.id.taka);
        TextView button = findViewById(R.id.donate);
        button.setOnClickListener(view -> {
            if (!(editText.getText().toString().equals(""))) {
                if (Integer.parseInt(editText.getText().toString()) > 0) {
                    Intent intentBrush = new Intent(getApplicationContext(), PaymentViewActivity.class);
                    intentBrush.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                    intentBrush.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    intentBrush.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    String orderId = String.valueOf((long) Math.floor(Math.random() * 9000000000L));
                    intentBrush.putExtra("donateURL", "http://phpstack-132936-652468.cloudwaysapps.com/Stripe/BookingPayement/make_payment?user_id=" + loginDTO.getId() +
                            "&order_id=" + orderId + "&user_name=" + loginDTO.getFirst_name() + "&amount=" + editText.getText());
                    intentBrush.putExtra("donateID", orderId);
                    startActivity(intentBrush);
                } else {
                    Toast.makeText(this, "Please select or enter a valid ammount", Toast.LENGTH_LONG).show();
                }
            }
        });
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        for (int takagula = 1; takagula <= 200; takagula++) {
            integerArrayList.add(takagula * 5);
        }
        ArrayAdapter<Integer> integerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, integerArrayList);
        listView.setAdapter(integerArrayAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            try {
                editText.setText(String.valueOf(integerArrayList.get(position)));
            }catch (Exception error){
                Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
