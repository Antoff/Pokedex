package ua.com.factory.antdroid.pokedex;

/**
 * Created by Ant on 01.08.2016.
 */
public class PokemonDbSchema {

    public static final class PokemonTable {
        public static final String NAME = "pokelist";

        public static final class Cols {
            public static final String ID = "_id";
            public static final String NAME = "name";
            public static final String WEIGHT = "weight";
            public static final String HEIGHT = "height";
            public static final String TYPE1 = "type1";
            public static final String TYPE2 = "type2";
            public static final String BASE_ATTACK = "base_attack";
            public static final String BASE_DEFENSE = "base_defense";
            public static final String BASE_STAMINA = "base_stamina";
            public static final String HP = "hp";
            public static final String ATTACK = "attack";
            public static final String DEFENSE = "defense";
            public static final String SP_ATTACK = "sp_attack";
            public static final String SP_DEFENSE = "sp_defense";
            public static final String SPEED = "speed";
            public static final String PREV_EVO = "prev_evo";
            public static final String NEXT_EVO = "next_evo";
            public static final String IMG = "img";
            public static final String PREV_EVO_ID = "prev_evo_id";
            public static final String NEXT_EVO_ID = "next_evo_id";
        }
    }

}
