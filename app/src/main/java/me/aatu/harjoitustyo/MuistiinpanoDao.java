package me.aatu.harjoitustyo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * Dao rajapinta tietokannan kyselyita varten
 */
@Dao
public interface MuistiinpanoDao {

    /**
     * Syottaa muistiinpanot tietokantaan
     * @param muistiinpano
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Muistiinpano... muistiinpano);

    /**
     * Hakee muistiinpanot tietokannasta
     * @return
     */
    @Query("SELECT * FROM muistiinpano ORDER BY last_modified DESC")
    Muistiinpano[] getAll();

    /**
     * Poistaa muistiinpanon tietokannasta
     * @param id
     */
    @Query("DELETE FROM muistiinpano where id=:id")
    void delete(int id);

}
