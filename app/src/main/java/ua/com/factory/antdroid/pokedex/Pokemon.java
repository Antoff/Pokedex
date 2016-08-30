package ua.com.factory.antdroid.pokedex;

/**
 * Created by Ant on 01.08.2016.
 */
public class Pokemon {

    private int mId;
    private String mName;
    private double mWeight;
    private double mHeight;
    private String mType1;
    private String mType2;
    private int mBaseAttack;
    private int mBaseDefense;
    private int mBaseStamina;
    private int mHP;
    private int mAttack;
    private int mDefense;
    private int mSpAttack;
    private int mSpDefense;
    private int mSpeed;
    private String mPrevEvo;
    private String mNextEvo;
    private String mImg;
    private int mPrevEvoID;
    private int mNextEvoID;

    private int mType1Color;
    private int mType2Color;

    public Pokemon(int id){
        this.mId = id;
    }

    public int getType1Color() {
        return mType1Color;
    }

    public int getType2Color() {
        return mType2Color;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public double getWeight() {
        return mWeight;
    }

    public void setWeight(double weight) {
        mWeight = weight;
    }

    public double getHeight() {
        return mHeight;
    }

    public void setHeight(double height) {
        mHeight = height;
    }

    public String getType1() {
        return mType1;
    }

    public void setType1(String type1) {
        mType1 = type1;

        switch (type1) {
           case "Normal": mType1Color = R.color.color_normal;
               break;
            case "Fire": mType1Color = R.color.color_fire;
                break;
            case "Water": mType1Color = R.color.color_water;
                break;
            case "Electric": mType1Color = R.color.color_electric;
                break;
            case "Grass": mType1Color = R.color.color_grass;
                break;
            case "Ice": mType1Color = R.color.color_ice;
                break;
            case "Fighting": mType1Color = R.color.color_fighting;
                break;
            case "Poison": mType1Color = R.color.color_poison;
                break;
            case "Ground": mType1Color = R.color.color_ground;
                break;
            case "Flying": mType1Color = R.color.color_flying;
                break;
            case "Psychic": mType1Color = R.color.color_psychic;
                break;
            case "Bug": mType1Color = R.color.color_bug;
                break;
            case "Rock": mType1Color = R.color.color_rock;
                break;
            case "Ghost": mType1Color = R.color.color_ghost;
                break;
            case "Dragon": mType1Color = R.color.color_dragon;
                break;
            case "Dark": mType1Color = R.color.color_dark;
                break;
            case "Steel": mType1Color = R.color.color_steel;
                break;
            case "Fairy": mType1Color = R.color.color_fairy;
                break;
            default: mType1Color = R.color.none;
        }

    }

    public String getType2() {
        return mType2;
    }

    public void setType2(String type2) {

        if (!type2.equals("none")) {
            mType2 = type2;
        } else mType2 = "";

        switch (type2) {
            case "Normal": mType2Color = R.color.color_normal;
                break;
            case "Fire": mType2Color = R.color.color_fire;
                break;
            case "Water": mType2Color = R.color.color_water;
                break;
            case "Electric": mType2Color = R.color.color_electric;
                break;
            case "Grass": mType2Color = R.color.color_grass;
                break;
            case "Ice": mType2Color = R.color.color_ice;
                break;
            case "Fighting": mType2Color = R.color.color_fighting;
                break;
            case "Poison": mType2Color = R.color.color_poison;
                break;
            case "Ground": mType2Color = R.color.color_ground;
                break;
            case "Flying": mType2Color = R.color.color_flying;
                break;
            case "Psychic": mType2Color = R.color.color_psychic;
                break;
            case "Bug": mType2Color = R.color.color_bug;
                break;
            case "Rock": mType2Color = R.color.color_rock;
                break;
            case "Ghost": mType2Color = R.color.color_ghost;
                break;
            case "Dragon": mType2Color = R.color.color_dragon;
                break;
            case "Dark": mType2Color = R.color.color_dark;
                break;
            case "Steel": mType2Color = R.color.color_steel;
                break;
            case "Fairy": mType2Color = R.color.color_fairy;
                break;
            default: mType2Color = R.color.none;
        }
    }

    public int getBaseAttack() {
        return mBaseAttack;
    }

    public void setBaseAttack(int baseAttack) {
        mBaseAttack = baseAttack;
    }

    public int getBaseDefense() {
        return mBaseDefense;
    }

    public void setBaseDefense(int baseDefense) {
        mBaseDefense = baseDefense;
    }

    public int getBaseStamina() {
        return mBaseStamina;
    }

    public void setBaseStamina(int baseStamina) {
        mBaseStamina = baseStamina;
    }

    public int getHP() {
        return mHP;
    }

    public void setHP(int HP) {
        mHP = HP;
    }

    public int getAttack() {
        return mAttack;
    }

    public void setAttack(int attack) {
        mAttack = attack;
    }

    public int getDefense() {
        return mDefense;
    }

    public void setDefense(int defense) {
        mDefense = defense;
    }

    public int getSpAttack() {
        return mSpAttack;
    }

    public void setSpAttack(int spAttack) {
        mSpAttack = spAttack;
    }

    public int getSpDefense() {
        return mSpDefense;
    }

    public void setSpDefense(int spDefense) {
        mSpDefense = spDefense;
    }

    public int getSpeed() {
        return mSpeed;
    }

    public void setSpeed(int speed) {
        mSpeed = speed;
    }

    public String getPrevEvo() {
        return mPrevEvo;
    }

    public void setPrevEvo(String prevEvo) {
        mPrevEvo = prevEvo;
    }

    public String getNextEvo() {
        return mNextEvo;
    }

    public void setNextEvo(String nextEvo) {
        mNextEvo = nextEvo;
    }

    public String getImg() {
        return mImg;
    }

    public void setImg(String img) {
        mImg = img;
    }

    public int getPrevEvoID() {
        return mPrevEvoID;
    }

    public void setPrevEvoID(int prevEvoID) {
        mPrevEvoID = prevEvoID;
    }

    public int getNextEvoID() {
        return mNextEvoID;
    }

    public void setNextEvoID(int nextEvoID) {
        mNextEvoID = nextEvoID;
    }
}
