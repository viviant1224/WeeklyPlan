package viviant.cn.weeklyplan.service;

import java.util.ArrayList;
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

    public List<Planthing> getDeletePlanthings() {
        planthingDBManager = PlanthingDBManager.getPlanthingDBManager();

        List<Planthing> planthingList = planthingDBManager.loadAll();
        List<Planthing> deletePlanthingList = new ArrayList<Planthing>();

        for (int i = 0; i < planthingList.size(); i++) {
            if (planthingList.get(i).getState() == 2) {
                deletePlanthingList.add(planthingList.get(i));
            }
        }

        return deletePlanthingList;
    }

    public List<Planthing> getUnDeletePlanthings() {
        planthingDBManager = PlanthingDBManager.getPlanthingDBManager();

        List<Planthing> planthingList = planthingDBManager.loadAll();
        List<Planthing> unDeletePlanthingList = new ArrayList<Planthing>();

        for (int i = 0; i < planthingList.size(); i++) {
            if (planthingList.get(i).getState() != 2) {
                unDeletePlanthingList.add(planthingList.get(i));
            }
        }

        return unDeletePlanthingList;
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
