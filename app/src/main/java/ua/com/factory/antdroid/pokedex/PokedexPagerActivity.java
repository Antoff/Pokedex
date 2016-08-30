package ua.com.factory.antdroid.pokedex;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class PokedexPagerActivity extends AppCompatActivity {
    private static final String EXTRA_POKEMON_ID =
            "ua.com.factory.antdroid.pokedex.pokemon_id";

    private ViewPager mViewPager;
    private List<Pokemon> mPokemons;

    public static Intent newIntent(Context pakageContext, int pokemonId) {
        Intent intent = new Intent(pakageContext, PokedexPagerActivity.class);
        intent.putExtra(EXTRA_POKEMON_ID, pokemonId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex_pager);

        int pokemonId = (int) getIntent().getIntExtra(EXTRA_POKEMON_ID , 0);

        mViewPager = (ViewPager) findViewById(R.id.activity_pokedex_pager_view_pager);

        mPokemons = PokeLab.get(this).getPokemons();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Pokemon pokemon = mPokemons.get(position);
                return PokedexFragment.newInstance(pokemon.getId());
            }

            @Override
            public int getCount() {
                return mPokemons.size();
            }
        });

        for (int i = 0; i < mPokemons.size(); i++) {
            if (mPokemons.get(i).getId() == pokemonId) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }
}
