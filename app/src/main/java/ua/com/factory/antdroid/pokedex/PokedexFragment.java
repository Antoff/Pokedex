package ua.com.factory.antdroid.pokedex;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ant on 01.08.2016.
 */
public class PokedexFragment extends Fragment{

    private static final String ARG_POKEMON_ID = "pokemon_id";

    private DataBaseHelper mDataBaseHelper = new DataBaseHelper(getActivity());
    private SQLiteDatabase mDatabase;

    private Pokemon mPokemon;
    private double mLevel;

    private ImageView pokemonImg;

    private ImageView imgPrevEvo;
    private ImageView imgNextEvo;

    private ImageView prevEvoLine1;
    private ImageView prevEvoLine2;
    private ImageView nextEvoLine1;
    private ImageView nextEvoLine2;

    private TextView pokemonName;
    private Spinner levelSpiner;

    private ImageView lineType1;
    private ImageView lineType2;
    private TextView number;

    private TextView pokemonType1;
    private TextView pokemonType2;
    private TextView pokemonWeight;
    private TextView pokemonHeight;

    /*Элементы статистики*/
    private TextView minCP;
    private ProgressBar pbCP;
    private TextView maxCP;
    private TextView minHP;
    private ProgressBar pbHP;
    private TextView maxHP;
    private TextView minAttack;
    private ProgressBar pbAttack;
    private TextView maxAttack;
    private TextView minDefense;
    private ProgressBar pbDefense;
    private TextView maxDefense;

    /*Описание*/
    private TextView description;

    public static PokedexFragment newInstance (int pokemonId) {
        Bundle args = new Bundle();
        args.putInt(ARG_POKEMON_ID, pokemonId);

        PokedexFragment fragment = new PokedexFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        int pokemonId = (int) getArguments().getInt(ARG_POKEMON_ID);
        mPokemon = PokeLab.get(getActivity()).getPokemon(pokemonId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view;


        if (mPokemon.getId() == 133) {
            view = inflater.inflate(R.layout.fragment_eevee, container, false);
        } else {
            view = inflater.inflate(R.layout.fragment_pokedex, container, false);
        }


        pokemonImg = (ImageView)view.findViewById(R.id.pokemon_img);
        imgPrevEvo = (ImageView)view.findViewById(R.id.img_evo_prev);
        imgNextEvo = (ImageView)view.findViewById(R.id.img_evo_next);

        prevEvoLine1 = (ImageView)view.findViewById(R.id.prev_line_type1);
        prevEvoLine2 = (ImageView)view.findViewById(R.id.prev_line_type2);
        nextEvoLine1  = (ImageView)view.findViewById(R.id.next_line_type1);
        nextEvoLine2 = (ImageView)view.findViewById(R.id.next_line_type2);

        pokemonName = (TextView)view.findViewById(R.id.pokemon_name);
        levelSpiner = (Spinner)view.findViewById(R.id.level_spiner);

        lineType1 = (ImageView)view.findViewById(R.id.line_type1);
        lineType2 = (ImageView)view.findViewById(R.id.line_type2);
        number = (TextView)view.findViewById((R.id.number));

        pokemonType1 = (TextView) view.findViewById(R.id.pokemon_type1);
        pokemonType2 = (TextView) view.findViewById(R.id.pokemon_type2);
        pokemonWeight = (TextView) view.findViewById(R.id.pokemon_weight);
        pokemonHeight = (TextView)view.findViewById(R.id.pokemon_height);

        /*Назначение элементов статистики*/
        minCP = (TextView) view.findViewById(R.id.cp_min_value);
        pbCP = (ProgressBar)view.findViewById(R.id.pb_cp);
        maxCP = (TextView)view.findViewById(R.id.cp_max_value);
        minHP = (TextView) view.findViewById(R.id.hp_min_value);
        pbHP = (ProgressBar)view.findViewById(R.id.pb_hp);
        maxHP = (TextView)view.findViewById(R.id.hp_max_value);
        minAttack = (TextView) view.findViewById(R.id.attack_min_value);
        pbAttack = (ProgressBar)view.findViewById(R.id.pb_attack);
        maxAttack = (TextView)view.findViewById(R.id.attack_max_value);
        minDefense = (TextView) view.findViewById(R.id.defense_min_value);
        pbDefense = (ProgressBar)view.findViewById(R.id.pb_defense);
        maxDefense = (TextView)view.findViewById(R.id.defense_max_value);

         /*Описание*/
        description = (TextView)view.findViewById(R.id.description);

        int imgResource = getResources().getIdentifier(mPokemon.getImg(), "drawable", "ua.com.factory.antdroid.pokedex");
        pokemonImg.setImageResource(imgResource);

        /*Заполнение PrevEvo*/
        if (mPokemon.getId() != 133) {
            if (mPokemon.getPrevEvoID() != 0) {
                int imgResPrevo = getResources().getIdentifier(
                        PokeLab.get(getActivity()).getPokemon(mPokemon.getPrevEvoID()).getImg(),
                        "drawable",
                        "ua.com.factory.antdroid.pokedex");
                imgPrevEvo.setImageResource(imgResPrevo);
                TextView imgPrevText = (TextView) view.findViewById(R.id.img_prev_text);
                imgPrevText.setText("PreEvo");
                TextView namePrev = (TextView) view.findViewById(R.id.name_prev);
                namePrev.setText(mPokemon.getPrevEvo());
                GridLayout gridLayout = (GridLayout) view.findViewById(R.id.prev_evo);
                gridLayout.setBackgroundResource(R.drawable.forname);

                int color1 = PokeLab.get(getActivity()).getPokemon(mPokemon.getPrevEvoID()).getType1Color();
                prevEvoLine1.setBackgroundResource(color1);
                int color2 = PokeLab.get(getActivity()).getPokemon(mPokemon.getPrevEvoID()).getType2Color();
                if (color2 != R.color.none) {
                    prevEvoLine2.setBackgroundResource(color2);
                } else prevEvoLine2.setBackgroundResource(color1);
            }
        }

        /*Заполнение NextEvo*/
        if (mPokemon.getId() != 133) {
            if (mPokemon.getNextEvoID() != 0) {
                int imgResNext = getResources().getIdentifier(
                        PokeLab.get(getActivity()).getPokemon(mPokemon.getNextEvoID()).getImg(),
                        "drawable",
                        "ua.com.factory.antdroid.pokedex");
                imgNextEvo.setImageResource(imgResNext);
                TextView imgNextText = (TextView) view.findViewById(R.id.img_next_text);
                imgNextText.setText("NextEvo");
                TextView nameNext = (TextView) view.findViewById(R.id.name_next);
                nameNext.setText(mPokemon.getNextEvo());
                GridLayout gridLayout = (GridLayout) view.findViewById(R.id.next_evo);
                gridLayout.setBackgroundResource(R.drawable.forname);

                int color1 = PokeLab.get(getActivity()).getPokemon(mPokemon.getNextEvoID()).getType1Color();
                nextEvoLine1.setBackgroundResource(color1);
                int color2 = PokeLab.get(getActivity()).getPokemon(mPokemon.getNextEvoID()).getType2Color();
                if (color2 != R.color.none) {
                    nextEvoLine2.setBackgroundResource(color2);
                } else nextEvoLine2.setBackgroundResource(color1);
            }
        }

        /*Spinner*/
        List<String> dataSpiner = dataSpinner();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, dataSpiner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelSpiner.setAdapter(adapter);
        levelSpiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                mLevel = Double.parseDouble(item);
                updateStats(mLevel);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        pokemonName.setText(mPokemon.getName());
        lineType1.setBackgroundResource(mPokemon.getType1Color());

        pokemonType1.setText(mPokemon.getType1() + " (" + rusType(mPokemon.getType1()) + ")");
        pokemonType1.setBackgroundResource(mPokemon.getType1Color());
        if (!mPokemon.getType2().equals("")) {
            pokemonType2.setText(mPokemon.getType2() + " (" + rusType(mPokemon.getType2()) + ")");
            pokemonType2.setBackgroundResource(mPokemon.getType2Color());
            lineType2.setBackgroundResource(mPokemon.getType2Color());
        } else lineType2.setBackgroundResource(mPokemon.getType1Color());

        pokemonWeight.setText(String.valueOf(mPokemon.getWeight()));
        pokemonHeight.setText(String.valueOf(mPokemon.getHeight()));

        number.setText(String.valueOf(mPokemon.getId()));

        updateStats(1);
        getDescription();

        return view;
    }

    /*Обновление данных статистики*/
    private void updateStats(double level){

        mLevel = level;
        BaseStats mBaseStats = BaseStats.get(getContext(), mLevel, mPokemon.getBaseAttack(),
                mPokemon.getBaseDefense(), mPokemon.getBaseStamina());
        minCP.setText(String.valueOf(mBaseStats.getMinCP()));
        maxCP.setText(String.valueOf(mBaseStats.getMaxCP()));
        pbCP.setMax(mBaseStats.getMaxCP());
        pbCP.setProgress(mBaseStats.getMinCP());
        minHP.setText(String.valueOf(mBaseStats.getMinHP()));
        maxHP.setText(String.valueOf(mBaseStats.getMaxHP()));
        pbHP.setMax(mBaseStats.getMaxHP());
        pbHP.setProgress(mBaseStats.getMinHP());
        minAttack.setText(String.valueOf(mBaseStats.getMinAttack()));
        maxAttack.setText(String.valueOf(mBaseStats.getMaxAttack()));
        pbAttack.setMax(mBaseStats.getMaxAttack());
        pbAttack.setProgress(mBaseStats.getMinAttack());
        minDefense.setText(String.valueOf(mBaseStats.getMinDefense()));
        maxDefense.setText(String.valueOf(mBaseStats.getMaxDefense()));
        pbDefense.setMax(mBaseStats.getMaxDefense());
        pbDefense.setProgress(mBaseStats.getMinDefense());

    }

    /*Возвращает описаие*/
    private void getDescription(){
        mDataBaseHelper.openDataBase();
        mDatabase = mDataBaseHelper.getMyDataBase();

        Cursor cursor = mDatabase.query("pokelist", new String[] {"_id", "description"},
                "_id = ?", new String[] {Integer.toString(mPokemon.getId())}, null, null, null);

        if (cursor.moveToNext()) {
            String text = cursor.getString(1);
            description.setText(text);
        }
        cursor.close();
    }

    /*Данные для Spinner*/
    private static ArrayList<String> dataSpinner() {

        ArrayList<String> list = new ArrayList<String>();

        for (int data = 1; data <= 40; data++) {
            double dData = data + 0.5;
            list.add(String.valueOf(data));
            if (data < 40) list.add(String.valueOf(dData));
        }
        return list;
    }

    /*Перевод типов на русский*/
    private static String rusType (String type){
        String rusType;
        switch (type) {
            case "Normal": rusType = "Обычный";
                break;
            case "Fire": rusType = "Огненный";
                break;
            case "Water": rusType = "Водный";
                break;
            case "Electric": rusType = "Электрический";
                break;
            case "Grass": rusType = "Травяной";
                break;
            case "Ice": rusType = "Ледяной";
                break;
            case "Fighting": rusType = "Боевой";
                break;
            case "Poison": rusType = "Ядовитый";
                break;
            case "Ground": rusType = "Земляной";
                break;
            case "Flying": rusType = "Летающий";
                break;
            case "Psychic": rusType = "Психический";
                break;
            case "Bug": rusType = "Насекомое";
                break;
            case "Rock": rusType = "Камянный";
                break;
            case "Ghost": rusType = "Призрак";
                break;
            case "Dragon": rusType = "Дракон";
                break;
            case "Dark": rusType = "Темный";
                break;
            case "Steel": rusType = "Стальной";
                break;
            case "Fairy": rusType = "Сказочный";
                break;
            default: rusType = "";
                break;
        }
        return rusType;
    }

}
