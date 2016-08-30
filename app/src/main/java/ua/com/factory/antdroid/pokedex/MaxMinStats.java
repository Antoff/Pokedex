package ua.com.factory.antdroid.pokedex;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MaxMinStats {

    private static MaxMinStats sMaxMinStats;

    private int maxBaseAttack = 0;
    private int minBaseAttack = Integer.MAX_VALUE;
    private int maxBaseDefense = 0;
    private int minBaseDefense = Integer.MAX_VALUE;
    private int maxBaseStamina = 0;
    private int minBaseStamina = Integer.MAX_VALUE;

    private Context mContext;
    private DataBaseHelper mDataBaseHelper = new DataBaseHelper(mContext);
    private SQLiteDatabase mDatabase;

    private MaxMinStats(Context context) {
        mContext = context;
        mDataBaseHelper.openDataBase();
        mDatabase = mDataBaseHelper.getMyDataBase();
    }

public static MaxMinStats get(Context context) {

    if (sMaxMinStats==null) {
        sMaxMinStats = new MaxMinStats(context);
        sMaxMinStats.getStats();
    }
    return sMaxMinStats;
}

    private void getStats () {
        Cursor cursor = queryStats(
                new String[]{
                        PokemonDbSchema.PokemonTable.Cols.BASE_ATTACK,
                        PokemonDbSchema.PokemonTable.Cols.BASE_DEFENSE,
                        PokemonDbSchema.PokemonTable.Cols.BASE_STAMINA},
                null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                if (sMaxMinStats.maxBaseAttack < cursor.getInt(0)) sMaxMinStats.maxBaseAttack = cursor.getInt(0);
                if (sMaxMinStats.minBaseAttack > cursor.getInt(0)) sMaxMinStats.minBaseAttack = cursor.getInt(0);
                if (sMaxMinStats.maxBaseDefense < cursor.getInt(1)) sMaxMinStats.maxBaseDefense = cursor.getInt(1);
                if (sMaxMinStats.minBaseDefense > cursor.getInt(1)) sMaxMinStats.minBaseDefense = cursor.getInt(1);
                if (sMaxMinStats.maxBaseStamina < cursor.getInt(2)) sMaxMinStats.maxBaseStamina = cursor.getInt(2);
                if (sMaxMinStats.minBaseStamina > cursor.getInt(2)) sMaxMinStats.minBaseStamina = cursor.getInt(2);
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
    }

    private Cursor queryStats (String[] whereColums, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                PokemonDbSchema.PokemonTable.NAME,
                whereColums,
                null,
                whereArgs,
                null, null, null);
        return cursor;
    }

    public int getMaxBaseAttack() {
        return maxBaseAttack;
    }

    public int getMinBaseAttack() {
        return minBaseAttack;
    }

    public int getMaxBaseDefense() {
        return maxBaseDefense;
    }

    public int getMinBaseDefense() {
        return minBaseDefense;
    }

    public int getMaxBaseStamina() {
        return maxBaseStamina;
    }

    public int getMinBaseStamina() {
        return minBaseStamina;
    }

}