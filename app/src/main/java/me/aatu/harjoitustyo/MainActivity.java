package me.aatu.harjoitustyo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

/**
 * Ohjelman paa luokka
 */
public class MainActivity extends AppCompatActivity {

    /**
     * MuistiinpanoDatabase olio, joka mahdollistaa helpon viittauksen tietokantaan
     */
    public static MuistiinpanoDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // luo tietokannan
        database = Room.databaseBuilder(
                        getApplicationContext(),
                        MuistiinpanoDatabase.class,
                        "muistiinpano-tietokanta"
                ).allowMainThreadQueries()
                .build();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}