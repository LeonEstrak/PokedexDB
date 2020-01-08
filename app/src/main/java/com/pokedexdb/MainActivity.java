package com.pokedexdb;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import me.sargunvohra.lib.pokekotlin.client.PokeApi;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;

public class MainActivity extends AppCompatActivity{

    //private AppBarConfiguration mAppBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mainactivity);


        final ListView listView = findViewById(R.id.pokelist);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (MainActivity.this, android.R.layout.simple_list_item_1);

        final Button button = findViewById(R.id.button);

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    PokeApi api = new PokeApiClient();
                    List<String> values = new ArrayList<>();
                    for (int i=1; i<=12; i++) { // number greater than 11 are not working
                        values.add(api.getPokemonSpecies(i).getName());
                        button.setText(i); // doesn't do anything for some reason
                    }
                    arrayAdapter.addAll(values);
                    button.setText("Refresh Completed"); // doesn't do anything for some reason
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //button.setText(new PokeApiClient().getPokemon(1).toString());
                arrayAdapter.notifyDataSetChanged();
            }
        });


        listView.setAdapter(arrayAdapter);

    }

}
