package viviant.cn.weeklyplan.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import viviant.cn.weeklyplan.bean.DaoMaster;
import viviant.cn.weeklyplan.bean.DaoSession;

/**
 * Created by weiwei.huang on 16-4-29.
 */
public abstract class AbstractDatabaseManager<M, K> implements IDatabase<M, K>{

    private static final String DEFAULT_DATABASE_NAME = "weeklyplan.db";

    /**
     * The Android Activity reference for access to DatabaseManager.
     */
    private static DaoMaster.DevOpenHelper mHelper;
    protected static DaoSession daoSession;




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
     * Query for readable DB
     */
    protected static void openReadableDb() throws SQLiteException {
        daoSession = new DaoMaster(getReadableDatabase()).newSession();
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

    /**
     * @return
     */
    private static SQLiteDatabase getReadableDatabase() {
        return mHelper.getReadableDatabase();
    }



    //
    @Override
    public boolean insert(@NonNull M m) {
        try {
            if (m == null)
                return false;
            openWritableDb();
            getAbstractDao().insert(m);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(@NonNull M m) {
        try {
            if (m == null)
                return false;
            openWritableDb();
            getAbstractDao().delete(m);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean update(@NonNull M m) {
        try {
            if (m == null)
                return false;
            openWritableDb();
            getAbstractDao().update(m);
        } catch (SQLiteException e) {
            return false;
        }
        return true;
    }

    @Override
    public M selectByPrimaryKey(@NonNull K key) {
        try {
            openReadableDb();
            return getAbstractDao().load(key);
        } catch (SQLiteException e) {
            return null;
        }
    }

    @Override
    public List<M> loadAll() {
        openReadableDb();
        return getAbstractDao().loadAll();
    }






}
