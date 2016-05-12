package viviant.cn.weeklyplan.service;

import java.util.List;

import viviant.cn.weeklyplan.bean.Planthing;
import viviant.cn.weeklyplan.db.PlanthingDBManager;

/**
 * Created by weiwei.huang on 16-5-12.
 */
public class PlanthingData {

    PlanthingDBManager planthingDBManager = null;

    public List<Planthing> getPlanthings() {
        planthingDBManager = PlanthingDBManager.getPlanthingDBManager();
        return planthingDBManager.loadAll();
    }

    public boolean insertPlanthing(Planthing planthing) {
        return PlanthingDBManager.getPlanthingDBManager().insert(planthing);
    }

    public Planthing getPlanthingById(long id) {
        return (Planthing)(PlanthingDBManager.getPlanthingDBManager().getObjectById(id+""));
    }

    public boolean deletePlanthing(long id) {
        return PlanthingDBManager.getPlanthingDBManager().delete(getPlanthingById(id));
    }

    public boolean updatePlanthing(Planthing planthing) {
        return PlanthingDBManager.getPlanthingDBManager().update(planthing);
    }
}
