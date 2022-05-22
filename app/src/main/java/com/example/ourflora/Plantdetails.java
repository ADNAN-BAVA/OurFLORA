package com.example.ourflora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.razorpay.Checkout;

import org.json.JSONException;
import org.json.JSONObject;

public class Plantdetails extends AppCompatActivity {
    ImageView i1;
    TextView t1,t2;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantdetails);
        i1=findViewById(R.id.backimage);
        t1 = findViewById(R.id.price_txt);
        t2 = findViewById(R.id.name_txt);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Plantdetails.this,MainActivity.class));
            }
        });

        Intent i = getIntent();
        String samount = t1.getText().toString();
        String sname = t2.getText().toString();


        int amount = Math.round(Float.parseFloat(samount)*100);
        b1 = findViewById(R.id.pay_btn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Checkout checkout = new Checkout();
                checkout.setKeyID("rzp_test_Rk8g7pfM8L4pce");

                JSONObject object = new JSONObject();
                try{
                    object.put("name",sname);
                    object.put("description","Test Payment");
                    object.put("theme.color","#0093DD");
                    object.put("currency","INR");
                    object.put("amount",amount);
                    object.put("prefill.contact","0000000000");
                    object.put("prefill.email","");

                    checkout.open(Plantdetails.this,object);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}