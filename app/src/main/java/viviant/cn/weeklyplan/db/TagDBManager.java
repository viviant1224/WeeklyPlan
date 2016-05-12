package viviant.cn.weeklyplan.db;

import de.greenrobot.dao.AbstractDao;
import viviant.cn.weeklyplan.bean.Role;
import viviant.cn.weeklyplan.bean.Tag;
import viviant.cn.weeklyplan.database.AbstractDatabaseManager;

/**
 * Created by weiwei.huang on 16-5-12.
 */
public class TagDBManager extends AbstractDatabaseManager<Tag, String> {

    @Override
    public AbstractDao getAbstractDao() {
        return daoSession.getTagDao();
    }

    @Override
    public Object getObjectById(String id) {
        Long tId = Long.parseLong(id);
        return daoSession.getTagDao().load(tId);
    }
}
