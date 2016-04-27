package viviant.cn.weeklyplan.model;

import java.io.Serializable;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: viviant
 * Date: 15-4-30
 * Time: 下午1:47
 * To change this template use File | Settings | File Templates.
 */
public class UserTag implements Serializable{
    private int id;
    private String tagName;
    private int state;
    private String establishDate;
    private Userinfo createUser;
    private boolean flagAvailiable;
    private String userTagDescription;
    private Set<Planthing> planthingSet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(String establishDate) {
        this.establishDate = establishDate;
    }

    public Userinfo getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Userinfo createUser) {
        this.createUser = createUser;
    }

    public String getUserTagDescription() {
        return userTagDescription;
    }

    public boolean isFlagAvailiable() {
        return flagAvailiable;
    }

    public void setFlagAvailiable(boolean flagAvailiable) {
        this.flagAvailiable = flagAvailiable;
    }

    public void setUserTagDescription(String userTagDescription) {
        this.userTagDescription = userTagDescription;
    }

    public Set<Planthing> getPlanthingSet() {
        return planthingSet;
    }

    public void setPlanthingSet(Set<Planthing> planthingSet) {
        this.planthingSet = planthingSet;
    }
}
