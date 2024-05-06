package com.example.lab4_20185910;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.example.lab4_20185910.databinding.ActivityMainBinding;
import com.example.lab4_20185910.dto.Geolocalizacion;
import com.example.lab4_20185910.services.Api_service;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Api_service api;
    Geolocalizacion geolocalizacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.buttonIngresar);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AppActivity.class);
            startActivity(intent);
        });
    }


    public boolean tieneInternet(){
        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        boolean tieneInternet = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        Log.d("msg-test","Internet: " + tieneInternet);
        return tieneInternet;
    }
}