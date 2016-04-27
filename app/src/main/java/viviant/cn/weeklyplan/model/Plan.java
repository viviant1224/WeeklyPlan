package viviant.cn.weeklyplan.model;

import java.io.Serializable;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: viviant
 * Date: 15-4-30
 * Time: 下午1:57
 * To change this template use File | Settings | File Templates.
 */
public class Plan implements Serializable{
    private int id;
    private String planDate;
    private String planName;
    private int planWeek;//哪一周
    private Userinfo userinfo;
    private Set<Planthing> planThingSet;
    private int state;    //计划状态，重要的计划，收藏的计划等  或者已经删除的计划
    private String planDescription;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public int getPlanWeek() {
        return planWeek;
    }

    public void setPlanWeek(int planWeek) {
        this.planWeek = planWeek;
    }

    public Userinfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }

    public Set<Planthing> getPlanThingSet() {
        return planThingSet;
    }

    public void setPlanThingSet(Set<Planthing> planThingSet) {
        this.planThingSet = planThingSet;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getPlanDescription() {
        return planDescription;
    }

    public void setPlanDescription(String planDescription) {
        this.planDescription = planDescription;
    }
}
