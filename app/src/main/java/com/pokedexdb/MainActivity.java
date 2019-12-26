package com.pokedexdb;

import android.os.Bundle;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import java.util.ArrayList;
import java.util.List;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;

public class MainActivity extends AppCompatActivity {

    //private AppBarConfiguration mAppBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mainactivity);


        final ListView listView = findViewById(R.id.pokelist);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (MainActivity.this, android.R.layout.simple_list_item_1);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {

                    PokeApi api = new PokeApiClient();

                    Looper.prepare();
                    List<String> values = new ArrayList<>();
                    for (int i=1; i<=5; i++)
                        values.add(api.getPokemonSpecies(i).getName());

                    arrayAdapter.addAll(values);


                }
                catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        };

        listView.setAdapter(arrayAdapter);

        thread.start();




    }

}
