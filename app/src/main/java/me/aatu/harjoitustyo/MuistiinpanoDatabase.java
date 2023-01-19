package me.aatu.harjoitustyo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Muistiinpanojen tietokannan luokka
 */
@Database(entities = {Muistiinpano.class}, version = 1)
public abstract class MuistiinpanoDatabase extends RoomDatabase {

    /**
     *
     * @return MuistiinpanoDao olio
     */
    public abstract MuistiinpanoDao muistiinpanoDao();
}
