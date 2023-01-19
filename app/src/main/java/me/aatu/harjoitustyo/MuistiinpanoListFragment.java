package me.aatu.harjoitustyo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Fragment muistiinpanojen listaukseen
 */
public class MuistiinpanoListFragment extends Fragment {

    public MuistiinpanoListFragment() {
        super(R.layout.fragment_muistiinpano_list);
    }

    /**
     * Hakee kaikki muistiinpanot ja luo niistä lista nakyman
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView rvMuistiinpanoList = view.findViewById(R.id.rvMuistiinpanoList);

        // hakee kaikki muistiinpanot
        Muistiinpano[] dataset = MainActivity.database.muistiinpanoDao().getAll();

        // asettaa otsikkoon muistiinpanojen määrä
        TextView otsikko = view.findViewById(R.id.tvOtsikko);
        otsikko.setText(getString(R.string.muistiinpanot_otsikko) + " (" + dataset.length + ")");

        // luo adapterin muistiinpanoista
        MuistiinpanoListAdapter adapter = new MuistiinpanoListAdapter(new ArrayList<>(Arrays.asList(dataset)));
        rvMuistiinpanoList.setAdapter(adapter);
        rvMuistiinpanoList.setLayoutManager(new LinearLayoutManager(getContext()));

        // uuden muistiinpanon luonti nappi
        Button btnUusi = view.findViewById(R.id.btnNew);
        btnUusi.setOnClickListener(v -> {
            // luo uuden muistiinpanon
            Muistiinpano r = new Muistiinpano();
            r.text = getString(R.string.uusi_sivu_teksti);
            r.lastModified = System.currentTimeMillis();

            // syöttää muistiinpanon tietokantaan
            MainActivity.database.muistiinpanoDao().insertAll(r);

            // päivittää muistiinpanon adapteriin
            adapter.addItem(r);
        });

        super.onViewCreated(view, savedInstanceState);
    }
}