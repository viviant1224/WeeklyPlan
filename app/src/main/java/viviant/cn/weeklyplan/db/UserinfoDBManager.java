package viviant.cn.weeklyplan.db;

import de.greenrobot.dao.AbstractDao;
import viviant.cn.weeklyplan.bean.Userinfo;
import viviant.cn.weeklyplan.database.AbstractDatabaseManager;

/**
 * Created by weiwei.huang on 16-4-29.
 */
public class UserinfoDBManager extends AbstractDatabaseManager<Userinfo, String> {

    @Override
    public AbstractDao getAbstractDao() {
        return daoSession.getUserinfoDao();
    }

    @Override
    public Object getObjectById(String id) {
        Long uId = Long.parseLong(id);
        return daoSession.getUserinfoDao().load(uId);
    }
}
