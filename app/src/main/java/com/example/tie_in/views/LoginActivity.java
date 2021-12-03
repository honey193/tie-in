

package com.example.tie_in.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tie_in.MainActivity;
import com.example.tie_in.data.Status;
import com.example.tie_in.databinding.ActivityLoginBinding;
import com.example.tie_in.repository.UserRepository;

public class LoginActivity extends AppCompatActivity {

    UserRepository userRepository;
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        userRepository = new UserRepository(this);

        binding.btnSignin.setOnClickListener(loginButton -> {
            if (binding.etMobileNo.getText().toString().equals("")) {
                Toast.makeText(LoginActivity.this, "please enter phone number", Toast.LENGTH_LONG).show();
            } else if (binding.etPassword.getText().toString().equals("")) {
                Toast.makeText(LoginActivity.this, "please enter password", Toast.LENGTH_LONG).show();
                return;
            } else {
                Status response = userRepository.login(binding.etMobileNo.getText().toString(), binding.etPassword.getText().toString());

                if (response.getCode().equals(Status.SUCCESS.getCode())) {
                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                    LoginActivity.this.startActivity(mainIntent);
                    LoginActivity.this.finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Error: Invalid Credentials!!", Toast.LENGTH_LONG).show();
                }
            }
        });

        binding.tvClickToSignin.setOnClickListener(v -> {
            Intent mainIntent = new Intent(LoginActivity.this, SignUpActivity.class);
            LoginActivity.this.startActivity(mainIntent);
            LoginActivity.this.finish();
        });

    }
}