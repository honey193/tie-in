package com.example.tie_in.views;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.DataSetObserver;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tie_in.MainActivity;
import com.example.tie_in.R;
import com.example.tie_in.data.Status;
import com.example.tie_in.databinding.ActivitySignUpBinding;
import com.example.tie_in.repository.UserRepository;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        userRepository = new UserRepository(SignUpActivity.this);
        binding.btnSignup.setOnClickListener(v -> {
            if (binding.etName.getText().toString().equals("")) {
                Toast.makeText(SignUpActivity.this, "Please Enter Name", Toast.LENGTH_LONG).show();
            } else if (binding.etPhone.getText().toString().equals("")) {
                Toast.makeText(SignUpActivity.this, "Please Enter Phone", Toast.LENGTH_LONG).show();
            } else {
                String response = Status.ERROR.getCode() + "Oops Something Went Wrong!";

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        response = userRepository.register(binding.etPhone.getText().toString(), binding.etName.getText().toString(), binding.etEmail.getText().toString());
                    } else{
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                    }
                }

                if (response.contains(Status.SUCCESS.getCode())) {
                    Intent mainIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                    SignUpActivity.this.startActivity(mainIntent);
                    SignUpActivity.this.finish();
                } else
                    Toast.makeText(SignUpActivity.this, response, Toast.LENGTH_LONG).show();
            }
        });

        binding.tvClickToSignin.setOnClickListener(v -> {
            Intent mainIntent = new Intent(SignUpActivity.this, LoginActivity.class);
            SignUpActivity.this.startActivity(mainIntent);
            SignUpActivity.this.finish();
        });
    }
}