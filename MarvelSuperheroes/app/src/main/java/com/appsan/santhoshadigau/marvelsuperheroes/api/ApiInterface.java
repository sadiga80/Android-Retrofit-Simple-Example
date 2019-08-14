package com.appsan.santhoshadigau.marvelsuperheroes.api;

import com.appsan.santhoshadigau.marvelsuperheroes.model.SuperHero;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    public String BASE_URL = "https://simplifiedcoding.net/demos/";

    @GET("marvel")
    Call<List<SuperHero>> getSuperHero();


}
