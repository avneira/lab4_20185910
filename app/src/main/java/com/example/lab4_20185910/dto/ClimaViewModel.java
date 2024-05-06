package com.example.lab4_20185910.dto;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class ClimaViewModel {
    private MutableLiveData<ArrayList<Geolocalizacion>> listaCiudadClima = new MutableLiveData<>();
    public MutableLiveData<ArrayList<Geolocalizacion>> getListaCiudadClima() {
        return listaCiudadClima;
    }
    public void setClima(List<Geolocalizacion> climas) {
        listaCiudadClima.setValue((ArrayList<Geolocalizacion>) climas);
    }
}
