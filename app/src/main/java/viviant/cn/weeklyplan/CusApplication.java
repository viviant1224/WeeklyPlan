package viviant.cn.weeklyplan;

import android.app.Application;
import android.util.Log;

import viviant.cn.weeklyplan.database.AbstractDatabaseManager;


/**
 * Created by weiwei.huang on 16-4-29.
 */
public class CusApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        initLogger();
        initOpenHelper();
    }

    private void initOpenHelper() {
        AbstractDatabaseManager.initOpenHelper(getApplicationContext());
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
