package me.aatu.harjoitustyo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Fragment muistiinpanon muokkaukseen
 */
public class MuistiinpanoMuokkausFragment extends Fragment {

    public MuistiinpanoMuokkausFragment() {
        super(R.layout.fragment_muistiinpano_muokkaus);
    }

    /**
     * Asettaa tiedot muistiinpanon muokkaus nakymaan
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // takaisin nappi, palaa muistiinpano sivulle
        Button takaisin = view.findViewById(R.id.btnTakas);
        takaisin.setOnClickListener(v -> getFragmentManager().popBackStack());

        // itse teksti
        EditText teksti = view.findViewById(R.id.etmlMuistiinpano);
        teksti.setText(getArguments().getString("text"));

        // tekstin tallennus tietokantaan
        Button tallenna = view.findViewById(R.id.btnTallenna);
        tallenna.setOnClickListener(v -> {
            Muistiinpano muistiinpano = new Muistiinpano();
            muistiinpano.id = getArguments().getInt("id");
            muistiinpano.text = teksti.getText().toString();
            muistiinpano.lastModified = System.currentTimeMillis();
            MainActivity.database.muistiinpanoDao().insertAll(muistiinpano);
        });
    }
}