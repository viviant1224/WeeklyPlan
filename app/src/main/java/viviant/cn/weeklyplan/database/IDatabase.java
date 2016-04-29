package viviant.cn.weeklyplan.database;


import de.greenrobot.dao.AbstractDao;

/**
 * Created by weiwei.huang on 16-4-29.
 */
public interface IDatabase<M, K> {
    /**
     *
     * @param m
     * @return
     */
    boolean insert(M m);


    AbstractDao<M, K> getAbstractDao();



}
