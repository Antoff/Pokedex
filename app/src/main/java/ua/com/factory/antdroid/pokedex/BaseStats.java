package ua.com.factory.antdroid.pokedex;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ant on 11.08.2016.
 */
public class BaseStats {

    private static BaseStats sBaseStats;
    private int minCP;
    private int maxCP;
    private int minHP;
    private int maxHP;
    private int minAttack;
    private int maxAttack;
    private int minDefense;
    private int maxDefense;

    private Context mContext;

    private BaseStats(Context context){
        mContext = context;
    }

    public static BaseStats get(Context context, double level, int baseAttack, int baseDefense, int baseStamina){
            sBaseStats = new BaseStats(context);
            sBaseStats.getStats(level, baseAttack, baseDefense, baseStamina);
        return sBaseStats;
    }

    public int getMinCP() {
        return minCP;
    }

    public int getMaxCP() {
        return maxCP;
    }

    public int getMinHP() {
        return minHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getMinAttack() {
        return minAttack;
    }

    public int getMaxAttack() {
        return maxAttack;
    }

    public int getMinDefense() {
        return minDefense;
    }

    public int getMaxDefense() {
        return maxDefense;
    }

    private void getStats(double level, int baseAttack, int baseDefense, int baseStamina) {
        double valueCPM = getCPMValue(level);
        sBaseStats.minCP = (int)Math.floor((baseAttack * Math.pow(baseDefense, 0.5) * Math.pow(baseStamina, 0.5) * Math.pow(valueCPM, 2))/10);
        sBaseStats.maxCP = (int)Math.floor(((baseAttack + 15) * Math.pow(baseDefense + 15, 0.5) * Math.pow(baseStamina + 15, 0.5) * Math.pow(valueCPM, 2))/10);
        sBaseStats.minHP = (int)Math.floor(baseStamina*valueCPM);
        sBaseStats.maxHP = (int)Math.floor((baseStamina+15)*valueCPM);
        sBaseStats.minAttack = (int)Math.floor(baseAttack*valueCPM);
        sBaseStats.maxAttack = (int)Math.floor((baseAttack+15)*valueCPM);
        sBaseStats.minDefense = (int)Math.floor(baseDefense*valueCPM);
        sBaseStats.maxDefense = (int)Math.floor((baseDefense+15)*valueCPM);

    }

    private static double getCPMValue(double level) {

        Map<Double, Double> cpm = new HashMap<Double, Double>();

        cpm.put(1.0, 0.094);
        cpm.put(1.5, 0.135137432);
        cpm.put(2.0, 0.16639787);
        cpm.put(2.5, 0.192650919);
        cpm.put(3.0, 0.21573247);
        cpm.put(3.5, 0.236572661);
        cpm.put(4.0, 0.25572005);
        cpm.put(4.5, 0.273530381);
        cpm.put(5.0, 0.29024988);
        cpm.put(5.5, 0.306057377);
        cpm.put(6.0, 0.3210876);
        cpm.put(6.5, 0.335445036);
        cpm.put(7.0, 0.34921268);
        cpm.put(7.5, 0.362457751);
        cpm.put(8.0, 0.37523559);
        cpm.put(8.5, 0.387592406);
        cpm.put(9.0, 0.39956728);
        cpm.put(9.5, 0.411193551);
        cpm.put(10.0, 0.42250001);
        cpm.put(10.5, 0.432926419);
        cpm.put(11.0, 0.44310755);
        cpm.put(11.5, 0.4530599578);
        cpm.put(12.0, 0.46279839);
        cpm.put(12.5, 0.472336083);
        cpm.put(13.0, 0.48168495);
        cpm.put(13.5, 0.4908558);
        cpm.put(14.0, 0.49985844);
        cpm.put(14.5, 0.508701765);
        cpm.put(15.0, 0.51739395);
        cpm.put(15.5, 0.525942511);
        cpm.put(16.0, 0.53435433);
        cpm.put(16.5, 0.542635767);
        cpm.put(17.0, 0.55079269);
        cpm.put(17.5, 0.558830576);
        cpm.put(18.0, 0.56675452);
        cpm.put(18.5, 0.574569153);
        cpm.put(19.0, 0.58227891);
        cpm.put(19.5, 0.589887917);
        cpm.put(20.0, 0.59740001);
        cpm.put(20.5, 0.604818814);
        cpm.put(21.0, 0.61215729);
        cpm.put(21.5, 0.619399365);
        cpm.put(22.0, 0.62656713);
        cpm.put(22.5, 0.633644533);
        cpm.put(23.0, 0.64065295);
        cpm.put(23.5, 0.647576426);
        cpm.put(24.0, 0.65443563);
        cpm.put(24.5, 0.661214806);
        cpm.put(25.0, 0.667934);
        cpm.put(25.5, 0.674577537);
        cpm.put(26.0, 0.68116492);
        cpm.put(26.5, 0.687680648);
        cpm.put(27.0, 0.69414365);
        cpm.put(27.5, 0.700538673);
        cpm.put(28.0, 0.70688421);
        cpm.put(28.5, 0.713164996);
        cpm.put(29.0, 0.71939909);
        cpm.put(29.5, 0.725571552);
        cpm.put(30.0, 0.7317);
        cpm.put(30.5, 0.734741009);
        cpm.put(31.0, 0.73776948);
        cpm.put(31.5, 0.740785574);
        cpm.put(32.0, 0.74378943);
        cpm.put(32.5, 0.746781211);
        cpm.put(33.0, 0.74976104);
        cpm.put(33.5, 0.752729087);
        cpm.put(34.0, 0.75568551);
        cpm.put(34.5, 0.758630378);
        cpm.put(35.0, 0.76156384);
        cpm.put(35.5, 0.764486065);
        cpm.put(36.0, 0.76739717);
        cpm.put(36.5, 0.770297266);
        cpm.put(37.0, 0.7731865);
        cpm.put(37.5, 0.776064962);
        cpm.put(38.0, 0.77893275);
        cpm.put(38.5, 0.781790055);
        cpm.put(39.0, 0.78463697);
        cpm.put(39.5, 0.787473578);
        cpm.put(40.0, 0.79030001);

        return cpm.get(level);

    }

}
