package viviant.cn.weeklyplan.preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by weiwei.huang on 16-5-11.
 */
public class WeeklyPlanSharePreference{

    public static  void saveFirstOpenData(Context context){
        SharedPreferences sp = context.getSharedPreferences("weeklyplan",  Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isFirstOpen", false);
        editor.commit();
    }


    public static boolean loadFirstOpenData(Context context) {
        SharedPreferences sp = context.getSharedPreferences("weeklyplan",  Context.MODE_PRIVATE);
        return sp.getBoolean("isFirstOpen", true);
    }

}
