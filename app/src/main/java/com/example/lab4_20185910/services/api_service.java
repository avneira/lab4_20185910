package com.example.lab4_20185910.services;

import com.example.lab4_20185910.dto.CiudadDto;

import retrofit2.Call;
import retrofit2.http.GET;

public interface api_service {
    @GET("q=London&limit=1&appid=8dd6fc3be19ceb8601c2c3e811c16cf1")
    Call<CiudadDto> obtenerCiudad();

}
