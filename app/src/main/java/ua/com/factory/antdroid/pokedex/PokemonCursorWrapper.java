package ua.com.factory.antdroid.pokedex;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * Created by Ant on 01.08.2016.
 */
public class PokemonCursorWrapper extends CursorWrapper {

    public PokemonCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Pokemon getPokemon() {
        int id = getInt(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.ID));
        String name = getString(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.NAME));
        double weight = getDouble(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.WEIGHT));
        double height = getDouble(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.HEIGHT));
        String type1 = getString(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.TYPE1));
        String type2 = getString(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.TYPE2));
        int baseAttack = getInt(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.BASE_ATTACK));
        int baseDefense = getInt(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.BASE_DEFENSE));
        int baseStamina = getInt(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.BASE_STAMINA));
        int hp = getInt(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.HP));
        int attack = getInt(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.ATTACK));
        int defense = getInt(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.DEFENSE));
        int spAttack = getInt(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.SP_ATTACK));
        int spDefense = getInt(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.SP_DEFENSE));
        int speed = getInt(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.SPEED));
        String prevEvo = getString(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.PREV_EVO));
        String nextEvo = getString(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.NEXT_EVO));

        String img = getString(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.IMG));

        int prevEvoID = getInt(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.PREV_EVO_ID));
        int nextEvoID = getInt(getColumnIndex(PokemonDbSchema.PokemonTable.Cols.NEXT_EVO_ID));

        Pokemon pokemon = new Pokemon(id);
        pokemon.setName(name);
        pokemon.setWeight(weight);
        pokemon.setHeight(height);
        pokemon.setType1(type1);
        pokemon.setType2(type2);
        pokemon.setBaseAttack(baseAttack);
        pokemon.setBaseDefense(baseDefense);
        pokemon.setBaseStamina(baseStamina);
        pokemon.setHP(hp);
        pokemon.setAttack(attack);
        pokemon.setDefense(defense);
        pokemon.setSpAttack(spAttack);
        pokemon.setSpDefense(spDefense);
        pokemon.setSpeed(speed);
        pokemon.setPrevEvo(prevEvo);
        pokemon.setNextEvo(nextEvo);
        pokemon.setImg(img);
        pokemon.setNextEvoID(nextEvoID);
        pokemon.setPrevEvoID(prevEvoID);

        return pokemon;
    }

}
