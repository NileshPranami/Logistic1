package com.creation.nilesh.logistic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignUpActivity extends AppCompatActivity {
    private EditText editText;
    private RadioGroup userGroup;
    private RadioButton radioButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth= FirebaseAuth.getInstance();
        starter();


    }

    private void starter(){
        userGroup=findViewById(R.id.radioGroup);
        editText=findViewById(R.id.phoneText);
        findViewById(R.id.sendButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected= userGroup.getCheckedRadioButtonId();
                radioButton=findViewById(selected);
                String number = editText.getText().toString().trim();

                if (number.isEmpty() || number.length() < 10) {
                    editText.setError("Valid number is required");
                    editText.requestFocus();
                    return;
                }
                number="+91"+number;

                String value = radioButton.getText().toString();
                Log.e("MyMessage","value is "+value);
                Intent intent = new Intent(SignUpActivity.this, OtpActivity.class);
                intent.putExtra("phonenumber", number);
                intent.putExtra("identity",value);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser!= null){
            Intent intent=new Intent(this,NavigationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }
}
