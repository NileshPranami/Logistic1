package com.creation.nilesh.logistic;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GetInformationActivity extends AppCompatActivity {
    private EditText userName;
    private EditText userEmail;
    private Button submitBtn;
    private String value;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_information);



        radioGroup = findViewById(R.id.radioGroup);
        userName = findViewById(R.id.editText);
        userEmail = findViewById(R.id.editText2);
        submitBtn = findViewById(R.id.button);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int selected= radioGroup.getCheckedRadioButtonId();
                radioButton=findViewById(selected);
                value = radioButton.getText().toString();

                sendUserData();
                if(value.equals( "I am User")){
                    Intent intent = new Intent(GetInformationActivity.this, NavigationCustomerActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(GetInformationActivity.this, NavigationDriver1Activity.class);
                    startActivity(intent);
                }



            }
        });

    }

    private void sendUserData() {
        String name = userName.getText().toString();
        String email = userEmail.getText().toString();
        FirebaseAuth firebaseAuth;
        firebaseAuth = FirebaseAuth.getInstance();
        String uId = firebaseAuth.getUid();
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();

        if(value.equals( "I am User")) {
            DatabaseReference myRef= firebaseDatabase.getReference().child("User").child("Customer").child(uId);
            CustomerData userProfile = new CustomerData(name, email);
            myRef.setValue(userProfile);
        }else {
            DatabaseReference myRef= firebaseDatabase.getReference().child("User").child("Driver").child(uId);
            DriverData driverData = new DriverData(name,email,"");
            myRef.setValue(driverData);
        }
    }
}
