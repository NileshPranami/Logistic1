package com.creation.nilesh.logistic;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class DriverSettingsActivity extends AppCompatActivity {

    private EditText mNameField, mPhoneField, mCarField;

    private Button mConfirm;

    private FirebaseAuth mAuth;
    private DatabaseReference mDriverDatabase;

    private String userID;
    private String mName;
    private String mPhone;
    private String vehicleNumber;
    private String mService;
    private RadioGroup mRadioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_settings);


        mNameField = (EditText) findViewById(R.id.name);
        mPhoneField = (EditText) findViewById(R.id.phone);
        mCarField = (EditText) findViewById(R.id.carrierNumber);


        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        mConfirm = (Button) findViewById(R.id.confirm);

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        mDriverDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(userID);

        getUserInfo();


        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInformation();
            }
        });

    }
    private void getUserInfo(){
        mDriverDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0){
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if(map.get("driverName")!=null){
                        mName = map.get("driverName").toString();
                        mNameField.setText(mName);
                    }
                    if(map.get("contactNumber")!=null){
                        mPhone = map.get("contactNumber").toString();
                        mPhoneField.setText(mPhone);
                    }
                    if(map.get("vehicleNumber")!=null){
                        vehicleNumber = map.get("vehicleNumber").toString();
                        mCarField.setText(vehicleNumber);
                    }
                    if(map.get("service")!=null){
                        mService = map.get("service").toString();
                        switch (mService){
                            case"Tempo":
                                mRadioGroup.check(R.id.tempo);
                                break;
                            case"PickUp / MiniVan":
                                mRadioGroup.check(R.id.pickUp);
                                break;
                            case"MiniTrucks":
                                mRadioGroup.check(R.id.miniTruck);
                                break;
                            case"TwoWheelers":
                                mRadioGroup.check(R.id.miniTruck);
                                break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }



    private void saveUserInformation() {
        mName = mNameField.getText().toString();
        mPhone = mPhoneField.getText().toString();
        vehicleNumber = mCarField.getText().toString();

        int selectId = mRadioGroup.getCheckedRadioButtonId();

        final RadioButton radioButton = findViewById(selectId);

        if (radioButton.getText() == null){
            return;
        }

        mService = radioButton.getText().toString();

        Map userInfo = new HashMap();
        userInfo.put("driverName", mName);
        userInfo.put("contactNumber", mPhone);
        userInfo.put("vehicleNumber", vehicleNumber);
        userInfo.put("service", mService);
        mDriverDatabase.updateChildren(userInfo);

        finish();

    }
}
