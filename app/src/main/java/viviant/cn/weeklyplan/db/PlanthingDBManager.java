package viviant.cn.weeklyplan.db;

import de.greenrobot.dao.AbstractDao;
import viviant.cn.weeklyplan.bean.Planthing;
import viviant.cn.weeklyplan.database.AbstractDatabaseManager;

/**
 * Created by weiwei.huang on 16-4-29.
 */
public class PlanthingDBManager extends AbstractDatabaseManager<Planthing, String> {

    @Override
    public AbstractDao getAbstractDao() {
        return daoSession.getPlanthingDao();
    }

    @Override
    public Object getObjectById(String id) {
        Long pId = Long.parseLong(id);
        return daoSession.getPlanthingDao().load(pId);
    }
}
