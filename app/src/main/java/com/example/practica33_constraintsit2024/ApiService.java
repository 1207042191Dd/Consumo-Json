package com.example.practica33_constraintsit2024;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("turismo/lugar_turistico/json_getlistadoGridLT")
    Call<ApiResponse> getLugaresTuristicos();
}
