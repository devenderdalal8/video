package com.devender.videoapp.Registration;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.devender.videoapp.MainActivity;
import com.devender.videoapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEmail, mPassword ;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        ImageButton mRegister = findViewById(R.id.register_button);
        ImageButton mPhone = findViewById(R.id.register_phone);
        mEmail = findViewById(R.id.register_email);
        mPassword = findViewById(R.id.register_password);

        mAuth = FirebaseAuth.getInstance();

        mRegister.setOnClickListener(v -> {
            final String email = mEmail.getText().toString();
            String password = mPassword.getText().toString();
            if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Account is Create SuccessFully...", Toast.LENGTH_SHORT).show();

                        Intent intent= new Intent(RegisterActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                    else
                    {
                        String message = task.getException().toString();
                        Toast.makeText(RegisterActivity.this, "Error : " + message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        mPhone.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, PhoneRegisterActivity.class);
            startActivity(intent);
        });
    }}

