package ua.com.factory.antdroid.pokedex;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ant on 01.08.2016.
 */
public class PokedexListFragment extends Fragment implements SearchView.OnQueryTextListener{

    private RecyclerView mPokedexRecyclerView;
    private PokedexAdapter mAdapter;
    List<Pokemon> pokemons;


    private MaxMinStats mMaxMinStats = MaxMinStats.get(getActivity());

    @Override
    public void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_pokedex_list, container, false);

        mPokedexRecyclerView = (RecyclerView)view.findViewById(R.id.pokedex_recycler_view);
        mPokedexRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onCreateOptionsMenu (Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_pokedex_list, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                mAdapter.setFilter(pokemons);
                return true;
            }
        });
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<Pokemon> filteredList = filter(pokemons, newText);
        mAdapter.setFilter(filteredList);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    private List<Pokemon> filter (List<Pokemon> pokemons, String query) {
        query = query.toLowerCase();

        final List<Pokemon> filteredList = new ArrayList<>();
        for (Pokemon pokemon: pokemons){
            final String text = pokemon.getName().toLowerCase();
            if (text.contains(query)) {
                filteredList.add(pokemon);
            }
        }
        return filteredList;
    }

    private void updateUI() {
        PokeLab pokeLab = PokeLab.get(getActivity());
        pokemons = pokeLab.getPokemons();
        mAdapter = new PokedexAdapter(pokemons);
        mPokedexRecyclerView.setAdapter(mAdapter);
    }

    private class PokedexHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Pokemon mPokemon;

        private TextView mNameText;
        private ImageView mImageView;

        private TextView mType1;
        private TextView mType2;

        private TextView mTVBaseAttack;
        private TextView mTVBaseDefense;
        private TextView mTVBaseStamina;

        private ProgressBar mPBarBaseAttack;
        private ProgressBar mPBarBaseDefense;
        private ProgressBar mPBarBaseStamina;

        public PokedexHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);

            mNameText =(TextView)itemView.findViewById(R.id.list_item_pokedex_name);
            mImageView = (ImageView)itemView.findViewById(R.id.list_item_pokedex_img);

            mType1 = (TextView)itemView.findViewById(R.id.list_item_pokedex_type1);
            mType2 = (TextView)itemView.findViewById(R.id.list_item_pokedex_type2);

            mPBarBaseAttack = (ProgressBar)itemView.findViewById(R.id.list_item_pokedex_pbar_base_attack);
            mPBarBaseDefense = (ProgressBar)itemView.findViewById(R.id.list_item_pokedex_pbar_base_defense);
            mPBarBaseStamina = (ProgressBar)itemView.findViewById(R.id.list_item_pokedex_pbar_base_stamina);

            mTVBaseAttack = (TextView)itemView.findViewById(R.id.list_item_pokedex_tv_base_attack);
            mTVBaseDefense = (TextView)itemView.findViewById(R.id.list_item_pokedex_tv_base_defense);
            mTVBaseStamina = (TextView)itemView.findViewById(R.id.list_item_pokedex_tv_base_stamina);


        }

        public void bindPokemon (Pokemon pokemon) {
            mPokemon = pokemon;

            int maxBAttack = mMaxMinStats.getMaxBaseAttack();
            int minBAttack = mMaxMinStats.getMinBaseAttack();
            int maxBDefense = mMaxMinStats.getMaxBaseDefense();
            int minBDefense = mMaxMinStats.getMinBaseDefense();
            int maxBStamina = mMaxMinStats.getMaxBaseStamina();
            int minBStamina = mMaxMinStats.getMinBaseStamina();

            mNameText.setText(mPokemon.getId()+ ". " + mPokemon.getName());

            int imgResource = getResources().getIdentifier(mPokemon.getImg(), "drawable", "ua.com.factory.antdroid.pokedex");
            mImageView.setImageResource(imgResource);

            mType1.setText(mPokemon.getType1());
            mType1.setBackgroundResource(mPokemon.getType1Color());

            mType2.setText(mPokemon.getType2());
            mType2.setBackgroundResource(mPokemon.getType2Color());

            mPBarBaseAttack.setMax(maxBAttack);
            mPBarBaseAttack.getProgressDrawable().setColorFilter(
                    colorProgBar(mPokemon.getBaseAttack(), maxBAttack, minBAttack),
                    android.graphics.PorterDuff.Mode.SRC_IN);
            mPBarBaseAttack.setProgress(mPokemon.getBaseAttack());
            mPBarBaseDefense.setMax(maxBDefense);
            mPBarBaseDefense.getProgressDrawable().setColorFilter(
                    colorProgBar(mPokemon.getBaseDefense(), maxBDefense, minBDefense),
                    android.graphics.PorterDuff.Mode.SRC_IN);
            mPBarBaseDefense.setProgress(mPokemon.getBaseDefense());
            mPBarBaseStamina.setMax(maxBStamina);
            mPBarBaseStamina.getProgressDrawable().setColorFilter(
                    colorProgBar(mPokemon.getBaseStamina(), maxBStamina, minBStamina),
                    android.graphics.PorterDuff.Mode.SRC_IN);
            mPBarBaseStamina.setProgress(mPokemon.getBaseStamina());

            mTVBaseAttack.setText(String.valueOf(mPokemon.getBaseAttack()));
            mTVBaseDefense.setText(String.valueOf(mPokemon.getBaseDefense()));
            mTVBaseStamina.setText(String.valueOf(mPokemon.getBaseStamina()));

        }

        @Override
        public void onClick(View v){
            Intent intent = PokedexPagerActivity.newIntent(getActivity(), mPokemon.getId());
            startActivity(intent);
        }

    }

    private int colorProgBar (int now, int max, int min){
        int color = Color.BLACK;

        if (now <= ((max - min)/3)+min) color =  Color.rgb(190, 5, 5);
        if (now <= (((max - min)/3)*2)+min && now > ((max - min)/3)+min ) color = Color.rgb(250, 150, 0);
        if (now > (((max - min)/3)*2)+min) color = Color.rgb(15, 160, 0);

        return color;
    }

    private class PokedexAdapter extends RecyclerView.Adapter<PokedexHolder>{
        private List<Pokemon> mPokemons;

        public PokedexAdapter(List<Pokemon> pokemons){
            mPokemons = pokemons;
        }

        @Override
        public PokedexHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_item_pokedex, parent, false);
            return new PokedexHolder(view);
        }

        @Override
        public void onBindViewHolder(PokedexHolder holder, int position) {
            Pokemon pokemon = mPokemons.get(position);
            holder.bindPokemon(pokemon);
        }

        public int getItemCount() {
            return mPokemons.size();
        }

        /*Фильтр для поиска*/
        public void setFilter(List<Pokemon> pokemons){
            mPokemons = new ArrayList<>();
            mPokemons.addAll(pokemons);
            notifyDataSetChanged();
        }

    }

}
