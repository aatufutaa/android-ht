package me.aatu.harjoitustyo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Luokka, joka sisaltaa muistiinpanon tiedot
 */
@Entity
public class Muistiinpano {

    /**
     * Jokaisella muistiinpanolla on uniikki tunniste, id, joka luodaan automaattisesti tietokannassa
     */
    @PrimaryKey(autoGenerate = true)
    public int id;

    /**
     * Muistiinpanon sisalto tekstina
     */
    @ColumnInfo(name="text")
    public String text;

    /**
     * Viimeisin tallennus aika millisekunteina
     */
    @ColumnInfo(name="last_modified")
    public long lastModified;

}
