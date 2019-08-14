package com.appsan.santhoshadigau.marvelsuperheroes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.appsan.santhoshadigau.marvelsuperheroes.api.ApiInterface;
import com.appsan.santhoshadigau.marvelsuperheroes.model.SuperHero;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = findViewById(R.id.listview);

        //creating retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //creating Api Interfece instance
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        //Calling API
        Call<List<SuperHero>> call = apiInterface.getSuperHero();

        //Parsing API
        call.enqueue(new Callback<List<SuperHero>>() {
            @Override
            public void onResponse(Call<List<SuperHero>> call, Response<List<SuperHero>> response) {
                List<SuperHero> superHeroes = response.body();

                String[] superHeroName = new String[superHeroes.size()];

                for (int i = 0; i < superHeroes.size(); i++) {
                    superHeroName[i] = superHeroes.get(i).getName();
                }

                listView.setAdapter(
                        new ArrayAdapter<String>(
                                getApplicationContext(),
                                android.R.layout.simple_list_item_1,
                                superHeroName
                        )
                );
            }

            @Override
            public void onFailure(Call<List<SuperHero>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
