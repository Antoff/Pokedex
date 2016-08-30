package ua.com.factory.antdroid.pokedex;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ant on 01.08.2016.
 */
public class PokeLab {

    private static PokeLab sPokeLab;

    private Context mContext;
    private DataBaseHelper mDataBaseHelper = new DataBaseHelper(mContext);
    private SQLiteDatabase mDatabase;

    public static PokeLab get(Context context) {
        if (sPokeLab == null){
            sPokeLab = new PokeLab(context);
        }
        return sPokeLab;
    }

    private PokeLab(Context context){
        mContext = context.getApplicationContext();

        mDataBaseHelper.openDataBase();
        mDatabase = mDataBaseHelper.getMyDataBase();

    }

    public List<Pokemon> getPokemons(){
        List<Pokemon> pokemons = new ArrayList<>();

        PokemonCursorWrapper cursor = queryPokemons(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                pokemons.add(cursor.getPokemon());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }

        return pokemons;
    }

    public Pokemon getPokemon(int id){

        PokemonCursorWrapper cursor = queryPokemons(
                PokemonDbSchema.PokemonTable.Cols.ID + " = ?",
                new String[] {String.valueOf(id)});

        try {
            if (cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getPokemon();
        }finally {
            cursor.close();
        }
    }

    private PokemonCursorWrapper queryPokemons(String whereClause, String[] whereArgs) {

        Cursor cursor = mDatabase.query(
                PokemonDbSchema.PokemonTable.NAME,
                null,
                whereClause,
                whereArgs,
                null, null, null);
        return new PokemonCursorWrapper(cursor);
    }

}
