package com.example.lab4_20185910.fragments;

import static android.provider.Contacts.SettingsColumns.KEY;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lab4_20185910.R;
import com.example.lab4_20185910.databinding.FragmentClimaBinding;
import com.example.lab4_20185910.databinding.FragmentGeolocalizacionBinding;
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


public class GeolocalizacionFragment extends Fragment {
    GeolocalizacionViewModel geolocalizacionViewModel;
    private FragmentGeolocalizacionBinding binding;
    Api_service apiService;
    private final List<Geolocalizacion> geolocalizacionList = new ArrayList<>(); // Inicializar la lista

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGeolocalizacionBinding.inflate(inflater, container, false);

        binding.buttonBuscar.setOnClickListener(v -> {
            String geoBuscar = binding.editTextText.getText().toString();
            binding.editTextText.getText().clear();

            if (geoBuscar.isEmpty()) {
                Toast.makeText(getContext(), "Ingrese una ciudad", Toast.LENGTH_LONG).show();
            } else {
                buscarGeolocalizacion(geoBuscar);
            }
        });

        geolocalizacionViewModel = new ViewModelProvider(requireActivity()).get(GeolocalizacionViewModel.class); // Inicializar el ViewModel

        geolocalizacionViewModel.getListaCiudadGeolo().observe(getViewLifecycleOwner(), geolocalizacions -> {
            updateRecyclerView(geolocalizacions);
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

        apiService.getGeolocalizacion(searchText, 1, KEY).enqueue(new Callback<List<Geolocalizacion>>() {
            @Override
            public void onResponse(@NonNull Call<List<Geolocalizacion>> call, @NonNull Response<List<Geolocalizacion>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Geolocalizacion> geolocalizaciones = response.body();
                    geolocalizacionList.addAll(geolocalizaciones); // Agregar las geolocalizaciones a la lista
                    geolocalizacionViewModel.setGeolocalizacion(geolocalizacionList); // Actualizar el ViewModel
                } else {
                    Toast.makeText(getContext(), "NO HAY DATOS DISPONIBLES", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Geolocalizacion>> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Error al obtener datos: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateRecyclerView(List<Geolocalizacion> geolocalizacion) {
        GeolocalizacionAdapter geoAdapter = new GeolocalizacionAdapter();
        geoAdapter.setContext(getContext()); // Corregir el nombre del método
        geoAdapter.setGelocalizacion(geolocalizacion); // Corregir el nombre del método
        binding.recyclerViewGeo.setAdapter(geoAdapter);
        binding.recyclerViewGeo.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
