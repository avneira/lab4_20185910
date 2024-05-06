package com.example.lab4_20185910.fragments;

import static android.provider.Contacts.SettingsColumns.KEY;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lab4_20185910.databinding.FragmentClimaBinding;
import com.example.lab4_20185910.databinding.FragmentGeolocalizacionBinding;
import com.example.lab4_20185910.dto.Clima;
import com.example.lab4_20185910.dto.ClimaViewModel;
import com.example.lab4_20185910.dto.Geolocalizacion;
import com.example.lab4_20185910.dto.GeolocalizacionAdapter;
import com.example.lab4_20185910.dto.GeolocalizacionViewModel;
import com.example.lab4_20185910.services.Api_service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClimaFragment extends Fragment {

    ClimaViewModel climaViewModel;
    private FragmentClimaBinding binding;
    Api_service apiService;
    private final List<Clima> climaList = new ArrayList<>(); // Inicializar la lista

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentClimaBinding.inflate(inflater, container, false);

        binding.button3.setOnClickListener(v -> {
            String geoBuscar = binding.editTextText.getText().toString();
            binding.editTextText.getText().clear();

            if (geoBuscar.isEmpty()) {
                Toast.makeText(getContext(), "Ingrese una ciudad", Toast.LENGTH_LONG).show();
            } else {
                buscarGeolocalizacion(geoBuscar);
            }
        });

        climaViewModel = new ViewModelProvider(requireActivity()).get(climaViewModel.class); // Inicializar el ViewModel

        climaViewModel.getListaCiudadClima().observe(getViewLifecycleOwner(), climas -> {
            updateRecyclerView(climas);
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void createRetrofitService() {
        apiService = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api_service.class);
    }

    private void buscarGeolocalizacion(String searchText) {
        createRetrofitService(); // Llamar al método para crear el servicio Retrofit

        apiService.getGeolocalizacion(searchText, 1, KEY).enqueue(new Callback<List<Clima>>() {
            @Override
            public void onResponse(@NonNull Call<List<Clima>> call, @NonNull Response<List<Clima>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Clima> climas = response.body();
                    climaList.addAll(climas); // Agregar las geolocalizaciones a la lista
                    climaViewModel.setClima(climas); // Actualizar el ViewModel
                } else {
                    Toast.makeText(getContext(), "NO HAY DATOS DISPONIBLES", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Clima>> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Error al obtener datos: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateRecyclerView(List<Geolocalizacion> geolocalizacion) {
        GeolocalizacionAdapter geoAdapter = new GeolocalizacionAdapter();
        geoAdapter.setContext(getContext()); // Corregir el nombre del método
        geoAdapter.setGelocalizacion(geolocalizacion); // Corregir el nombre del método
        binding.recyclerViewClima.setAdapter(geoAdapter);
        binding.recyclerViewClima.setLayoutManager(new LinearLayoutManager(getContext()));
    }


}