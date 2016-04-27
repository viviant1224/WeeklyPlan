package viviant.cn.weeklyplan.model;

import java.io.Serializable;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: viviant
 * Date: 15-4-30
 * Time: 下午1:52
 * To change this template use File | Settings | File Templates.
 */
public class Planthing implements Serializable{
    private int id;
    private String planthingDescription;
    private boolean flagRemind;//设置是否闹钟提醒
    private UserTag userTag;
    private UserRole userRole;
    private int state;
    private Plan plan;
    private String planthingDate;
    private String planthingName;
    private String doDateTime;    //什么时候做这件事情
    private String remindDate;//闹钟提醒时间
    private Level level;
    private String planThingPlace;
    private Userinfo userinfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlanthingDescription() {
        return planthingDescription;
    }

    public void setPlanthingDescription(String planthingDescription) {
        this.planthingDescription = planthingDescription;
    }

    public boolean isFlagRemind() {
        return flagRemind;
    }

    public void setFlagRemind(boolean flagRemind) {
        this.flagRemind = flagRemind;
    }

    public UserTag getUserTag() {
        return userTag;
    }

    public void setUserTag(UserTag userTag) {
        this.userTag = userTag;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public String getPlanthingDate() {
        return planthingDate;
    }

    public void setPlanthingDate(String planthingDate) {
        this.planthingDate = planthingDate;
    }

    public String getPlanthingName() {
        return planthingName;
    }

    public void setPlanthingName(String planthingName) {
        this.planthingName = planthingName;
    }

    public String getDoDateTime() {
        return doDateTime;
    }

    public void setDoDateTime(String doDateTime) {
        this.doDateTime = doDateTime;
    }

    public String getRemindDate() {
        return remindDate;
    }

    public void setRemindDate(String remindDate) {
        this.remindDate = remindDate;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getPlanThingPlace() {
        return planThingPlace;
    }

    public void setPlanThingPlace(String planThingPlace) {
        this.planThingPlace = planThingPlace;
    }

    public Userinfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }


}
