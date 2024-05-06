package com.example.lab4_20185910.dto;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class GeolocalizacionViewModel {


    private MutableLiveData<ArrayList<Geolocalizacion>> listaCiudadGeolo = new MutableLiveData<>();



    public MutableLiveData<ArrayList<Geolocalizacion>> getListaCiudadGeolo () {
        return listaCiudadGeolo;
    }

    public void setGeolocalizacion(List<Geolocalizacion> geolocalizaciones) {
        listaCiudadGeolo.setValue((ArrayList<Geolocalizacion>) geolocalizaciones);
    }

}
