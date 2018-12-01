package com.drmmx.devmax.moappspreviewer.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.drmmx.devmax.moappspreviewer.R;
import com.drmmx.devmax.moappspreviewer.model.SignInData;
import com.drmmx.devmax.moappspreviewer.model.UserResponse;
import com.drmmx.devmax.moappspreviewer.retrofit.MoAppsAPI;
import com.drmmx.devmax.moappspreviewer.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SignInActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        progressBar = findViewById(R.id.progressBar);
        Button signInButton = findViewById(R.id.signInButton);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if (!editTextEmail.getText().toString().equals("")
                        && !editTextPassword.getText().toString().equals("")
                        && editTextEmail.getText().toString().contains(".")
                        && editTextEmail.getText().toString().contains("@")) {
                    getSignInRequest();
                } else {
                    Toast.makeText(SignInActivity.this, "Пожалуйста, введите правильный e-mail и пароль", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void getSignInRequest() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_SIGN_IN_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MoAppsAPI service = retrofit.create(MoAppsAPI.class);

        Call<UserResponse> call = service.getStringSignIn(new SignInData(email, password));
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                if (response.isSuccessful() && response.body().getCode() == 200) {
                    Intent intent = new Intent(SignInActivity.this, PreviewActivity.class);
                    intent.putExtra(Constants.TOKEN_RESPONSE, response.body().getData());
                    startActivity(intent);
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
                Toast.makeText(SignInActivity.this, "Ошибка" + t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
