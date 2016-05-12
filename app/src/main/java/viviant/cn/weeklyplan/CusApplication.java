package viviant.cn.weeklyplan;

import android.app.Application;
import android.util.Log;

import viviant.cn.weeklyplan.database.AbstractDatabaseManager;
import viviant.cn.weeklyplan.database.InsertTagLevelRoleDatabase;
import viviant.cn.weeklyplan.preference.WeeklyPlanSharePreference;


/**
 * Created by weiwei.huang on 16-4-29.
 */
public class CusApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        initLogger();
        initOpenHelper();

        if (isFirstTimeOpen()) {
            Log.d("weiwei", "is first");
            InsertTagLevelRoleDatabase.initDatabase();//注册完账户调用，测试阶段，提前调用
            WeeklyPlanSharePreference.saveFirstOpenData(getApplicationContext());
        } else {
            Log.d("weiwei", "not first");
        }
    }

    private void initOpenHelper() {
        AbstractDatabaseManager.initOpenHelper(getApplicationContext());
    }

    private boolean isFirstTimeOpen() {
        return WeeklyPlanSharePreference.loadFirstOpenData(getApplicationContext());
    }






    /**
     * 初始化配置Logger
     */
    private void initLogger() {
        Log.d("weiwei", "*********");
//        Settings settings = Logger.init(LOGGER_TAG)               // default PRETTYLOGGER or use just init()
//                .setMethodCount(2).hideThreadInfo();            // default 2
//        if (isDebug) {
//            settings.setLogLevel(LogLevel.NONE);
//        } else {
//            settings.setLogLevel(LogLevel.FULL);
//            initCrashHandler();
//
//        }
    }



}
