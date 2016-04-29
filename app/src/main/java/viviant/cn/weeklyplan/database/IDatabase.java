package viviant.cn.weeklyplan.database;


import java.util.List;

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

    /**
     *
     * @param m
     * @return
     */
    boolean delete(M m);




    Object getObjectById(String id);

    M selectByPrimaryKey(K key);

    List<M> loadAll();

    boolean update(M m);

    AbstractDao<M, K> getAbstractDao();



}
