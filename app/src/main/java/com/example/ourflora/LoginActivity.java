package com.example.ourflora;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    TextView t1;
    Button login_button;
    //ImageView logo;
    //TextView logotext, slogantext;
    TextInputLayout email, pass;
    EditText email_addr, pass_addr;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        t1 = findViewById(R.id.signup_txt);
        login_button = findViewById(R.id.login_btn);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        email_addr = findViewById(R.id.email_edttxt);
        pass_addr = findViewById(R.id.password_edttxt);

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
        login_button.setOnClickListener(view -> {
            if(!email_addr.getText().toString().isEmpty()&&!pass_addr.getText().toString().isEmpty()) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "Enter valid crediantials", Toast.LENGTH_SHORT).show();
        });
    }

    public void login(View v) {

        String email_key = email_addr.getText().toString();
        String pass_key = pass_addr.getText().toString();
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email_key, pass_key).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Log.d("mmm", "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("mmm", "signInWithEmail:failure", task.getException());
                    Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                }
            }

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null)
        {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

}