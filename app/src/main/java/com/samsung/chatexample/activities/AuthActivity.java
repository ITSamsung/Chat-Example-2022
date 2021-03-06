package com.samsung.chatexample.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.samsung.chatexample.R;
import com.samsung.chatexample.listeners.MyValueEventListener;
import com.samsung.chatexample.services.AuthService;

import java.util.Random;

public class AuthActivity extends AppCompatActivity {
    EditText editTextEmail;
    EditText editTextName;
    EditText editTextPassword;
    Button createAcc;
    Button logIn;

    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        initViews();

        editTextEmail.setText(
                "test"+new Random().nextInt(10000)+"@gmail.com"
        );

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });
    }

    void initViews() {
        editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        editTextName = findViewById(R.id.editTextTextPersonName);
        editTextPassword = findViewById(R.id.editTextTextPassword);

        createAcc = findViewById(R.id.createAccButton);
        logIn = findViewById(R.id.logInButton);
        bar = findViewById(R.id.progressBar);
    }

    void createAccount() {
        if (editTextEmail.getText().toString().isEmpty()) {
            showMessage("Email is empty");
            return;
        }
        if (editTextName.getText().toString().isEmpty()) {
            showMessage("Name is empty");
            return;
        }
        if (editTextPassword.getText().toString().isEmpty()) {
            showMessage("Password is empty");
            return;
        }

        bar.setVisibility(View.VISIBLE);
        AuthService.createAccount(
                        editTextEmail.getText().toString(),
                        editTextName.getText().toString(),
                        editTextPassword.getText().toString(),
                        new MyValueEventListener<String>() {
                            @Override
                            public void onValue(String value) {
                                showMessage("User created!");
                                startChatActivity(value);
                            }
                        }
                ).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showMessage("Can't create user");
                        e.printStackTrace();
                    }
                })
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        bar.setVisibility(View.GONE);
                    }
                });
    }

    void logIn() {
        if (editTextEmail.getText().toString().isEmpty()) {
            showMessage("Email is empty");
            return;
        }
        if (editTextPassword.getText().toString().isEmpty()) {
            showMessage("Password is empty");
            return;
        }

        //TODO
    }

    void startChatActivity(String userKey) {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("USER_KEY", userKey);
        startActivity(intent);
    }

    void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}