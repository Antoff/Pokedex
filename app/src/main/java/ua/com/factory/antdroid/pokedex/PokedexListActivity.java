package ua.com.factory.antdroid.pokedex;

import android.support.v4.app.Fragment;

import java.io.IOException;

/**
 * Created by Ant on 01.08.2016.
 */
public class PokedexListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment(){
        DataBaseHelper mDataBaseHelper = new DataBaseHelper(this);
        try {
            mDataBaseHelper.createDataBase();
        } catch (IOException e) {
            throw new Error("Unable to create database");
        }

        return new PokedexListFragment();
    }

}
