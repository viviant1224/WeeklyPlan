package viviant.cn.weeklyplan.db;

import de.greenrobot.dao.AbstractDao;
import viviant.cn.weeklyplan.bean.Role;
import viviant.cn.weeklyplan.database.AbstractDatabaseManager;

/**
 * Created by weiwei.huang on 16-5-12.
 */
public class RoleDBManager extends AbstractDatabaseManager<Role, String> {

    @Override
    public AbstractDao getAbstractDao() {
        return daoSession.getRoleDao();
    }

    @Override
    public Object getObjectById(String id) {
        Long rId = Long.parseLong(id);
        return daoSession.getRoleDao().load(rId);
    }
}
