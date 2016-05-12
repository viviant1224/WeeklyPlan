package viviant.cn.weeklyplan.db;

import de.greenrobot.dao.AbstractDao;
import viviant.cn.weeklyplan.bean.Level;
import viviant.cn.weeklyplan.database.AbstractDatabaseManager;

/**
 * Created by weiwei.huang on 16-5-11.
 */
public class LevelDBManager extends AbstractDatabaseManager<Level, String> {

    @Override
    public AbstractDao getAbstractDao() {
        return daoSession.getLevelDao();
    }

    @Override
    public Object getObjectById(String id) {
        Long lId = Long.parseLong(id);
        return daoSession.getLevelDao().load(lId);
    }
}
