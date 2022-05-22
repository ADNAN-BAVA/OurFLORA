package com.example.ourflora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
    TextView login;
    TextInputLayout regEmail,regPassword,regName,regMobile;
    Button signup;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        login = findViewById(R.id.login_txt);
        regName = findViewById(R.id.name_edttxt);
        regEmail = findViewById(R.id.email_edttxt);
        regMobile = findViewById(R.id.mobile_edttxt);
        regPassword = findViewById(R.id.password_edttxt);
        signup = findViewById(R.id.signup_btn);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RegisterActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
            }
        });

    }

    private Boolean validateName() {
        String val = regName.getEditText().getText().toString();
        if(val.isEmpty())
        {
            regName.setError("Field cannot be Empty");
            return false;
        }
        else{
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();
        String pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(val.isEmpty())
        {
            regEmail.setError("Field cannot be Empty");
            return false;
        }
        else if(!val.matches(pattern))
        {
            regEmail.setError("Invalid Email Address");
            return false;
        }
        else{
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validateMobile() {
        String val = regMobile.getEditText().getText().toString();
        if(val.isEmpty())
        {
            regMobile.setError("Field cannot be Empty");
            return false;
        }
        else{
            regMobile.setError(null);
            regMobile.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
        if(val.isEmpty())
        {
            regPassword.setError("Field cannot be Empty");
            return false;
        }

        else{
            regPassword.setError(null);
            return true;
        }

    }
    public void registerUser(View v)
    {
        if(!validateName()| !validateEmail() | !validateMobile() | !validatePassword())
        {
            Toast.makeText(this, "Enter the details", Toast.LENGTH_SHORT).show();
            return;
        }

        String name = regName.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String mobile = regMobile.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();


        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        myRef = database.getReference("/");
        myRef= myRef.child("Users");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(name).getValue()==null)
                {
                        Log.i("ccc","sdfghjk");
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Log.i("bava","some msg");
                                myRef = myRef.child(name);
                                myRef.child("Name : ").setValue(name);
                                myRef.child("Email : ").setValue(email);
                                myRef.child("Phone : ").setValue(mobile);
                                myRef.child("Password : ").setValue(password);
                                Toast.makeText(getApplicationContext(), "Register Successful", Toast.LENGTH_SHORT).show();

                            }
                            else
                            {
                                Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
                else {
                    Toast.makeText(getApplicationContext(), "Username already exits", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}