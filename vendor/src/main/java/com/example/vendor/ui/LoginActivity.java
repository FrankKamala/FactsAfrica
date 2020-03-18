package com.example.vendor.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.vendor.Constants;
import com.example.vendor.R;
import com.example.vendor.models.Login;
import com.example.vendor.models.User;
import com.example.vendor.network.FactsAfricaApi;
import com.example.vendor.network.FactsAfricaClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    @BindView(R.id.mail) EditText mEmail;
    @BindView(R.id.password) EditText mPassword;
    @BindView(R.id.login) Button mLogin;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mProgressBar = findViewById(R.id.login_progress_bar);
        ButterKnife.bind(this);
        mLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mLogin) {
            mProgressBar.setVisibility(View.VISIBLE);
            login(mEmail.getText().toString(), mPassword.getText().toString());
        }
    }

    private void login(String email, String password) {
        Login login = new Login(email, password);
        FactsAfricaApi service = FactsAfricaClient.getClient().create(FactsAfricaApi.class);
        Call<User> call = service.login(login, Constants.ACCEPT_TYPE);
        Log.v("MY URL", call.request().url().toString());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(LoginActivity.this, "Welcome " + response.body().getName(), Toast.LENGTH_SHORT).show();
                    String token = response.body().getApiToken();
                    String bearerToken = "Bearer " + token;
                    mPreferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                    mEditor = mPreferences.edit();
                    mEditor.putString("token", bearerToken);
                    mEditor.commit();
                    Intent homeIntent = new Intent(LoginActivity.this, BottomNavigation.class);
                    startActivity(homeIntent);
                    finish();
                } else {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(LoginActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mProgressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
