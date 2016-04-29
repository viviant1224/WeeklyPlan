package viviant.cn.weeklyplan.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import viviant.cn.weeklyplan.bean.DaoMaster;
import viviant.cn.weeklyplan.bean.DaoSession;

/**
 * Created by weiwei.huang on 16-4-29.
 */
public class AbstractDatabaseManager implements IDatabase{

    private static final String DEFAULT_DATABASE_NAME = "weeklyplan.db";

    /**
     * The Android Activity reference for access to DatabaseManager.
     */
    private static DaoMaster.DevOpenHelper mHelper;
    protected static DaoSession daoSession;

    @Override
    public boolean insert() {
        return false;
    }


    /**
     * 初始化OpenHelper
     *
     * @param context
     */
    public static void initOpenHelper(@NonNull Context context) {
        mHelper = getOpenHelper(context, DEFAULT_DATABASE_NAME);
        openWritableDb();
    }

    /**
     * Query for writable DB
     */
    protected static void openWritableDb() throws SQLiteException {
        daoSession = new DaoMaster(getWritableDatabase()).newSession();
    }

    /**
     * 只关闭helper就好,看源码就知道helper关闭的时候会关闭数据库
     *
     */
    public static void closeDbConnections() {
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
    }

    /**
     * 在applicaiton中初始化DatabaseHelper
     */
    private static DaoMaster.DevOpenHelper getOpenHelper(@NonNull Context context, @Nullable String dataBaseName) {
        closeDbConnections();
        return new DaoMaster.DevOpenHelper(context, dataBaseName, null);
    }

    /**
     * @return
     */
    private static SQLiteDatabase getWritableDatabase() {
        return mHelper.getWritableDatabase();
    }

}
