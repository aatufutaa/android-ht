package me.aatu.harjoitustyo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter luokka listan luontiin
 */
public class MuistiinpanoListAdapter extends RecyclerView.Adapter<MuistiinpanoListAdapter.ViewHolder> {

    /**
     * Lista muistiinpanoista
     */
    private final List<Muistiinpano> localDataset;

    /**
     * Kuuntelija muistiinpanojen painallukseen
     */
    private final View.OnClickListener listener = (view) -> {
        int position = (int) view.getTag();

        // tarvittavat tiedot bundleen
        Bundle bundle = new Bundle();
        Muistiinpano muistiinpano = MuistiinpanoListAdapter.this.localDataset.get(position);
        bundle.putInt("id", muistiinpano.id);
        bundle.putString("text", muistiinpano.text);

        // näytetään muokkaus sivu
        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.muistiinpano_muokkaus_fragment, bundle);
    };

    public MuistiinpanoListAdapter(List<Muistiinpano> dataset) {
        this.localDataset = dataset;
    }

    /**
     * Luo ViewHolder olion muistiinpanoista
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.muistiinpano_list_item, parent, false));
    }

    /**
     * Asettaa otsikon ja toisen rivin muistiinpanolle
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this.listener);

        Muistiinpano muistiinpano = localDataset.get(position);

        // luo otsikon muistiinpanoista (rajoitettu 24 merkkiin)
        String[] rivit = muistiinpano.text.split("\n");
        String otsikko = rivit[0];
        if (otsikko.length() > 24) {
            otsikko = otsikko.substring(0, 24) + "...";
        }
        holder.ensimmainenRivi.setText(otsikko);

        // luo ali otsikon muistiinpanojen toisesta rivista (40 merkkiä)
        String toinenRivi = "";
        if (rivit.length > 1) {
            toinenRivi = rivit[1];
            if (toinenRivi.length() > 40) {
                toinenRivi = toinenRivi.substring(0, 40) + "...";
            }
        }
        holder.toinenRivi.setText(toinenRivi);

        // poistaa muistiinpanon tietokannasta ja listasta
        holder.poista.setOnClickListener(view -> {
            MainActivity.database.muistiinpanoDao().delete(muistiinpano.id);
            this.localDataset.remove(position);
            this.notifyItemRemoved(position);
            this.notifyItemRangeChanged(position, this.localDataset.size());
        });
    }

    /**
     *
     * @return data kokoelman koko
     */
    @Override
    public int getItemCount() {
        return this.localDataset.size();
    }

    /**
     * ViewHolder yksittaisen muistiinpanon otsikolle, toiselle riville seka napille
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView ensimmainenRivi;
        public final TextView toinenRivi;
        public final Button poista;

        public ViewHolder(View view) {
            super(view);

            this.ensimmainenRivi = view.findViewById(R.id.tvEnsimmainenRivi);
            this.toinenRivi = view.findViewById(R.id.tvToinenRivi);
            this.poista = view.findViewById(R.id.btnPoista);
        }
    }

    /**
     *
     * @param muistiinpano
     *
     * Lisää muistiinpano olion data kokoelmaan ja paivittaa listan
     */
    public void addItem(Muistiinpano muistiinpano) {
        this.localDataset.add(muistiinpano);
        this.notifyItemInserted(this.localDataset.size() - 1);
    }
}
