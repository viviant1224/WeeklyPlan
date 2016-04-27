package viviant.cn.weeklyplan.model;

import java.io.Serializable;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: viviant
 * Date: 15-4-30
 * Time: 下午1:50
 * To change this template use File | Settings | File Templates.
 */
public class Role implements Serializable{
    private int id;
    private String roleName;
    private int state;
    private String establishDate;
    private boolean flagAvailiable;
    private String roleDescription;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public boolean isFlagAvailiable() {
        return flagAvailiable;
    }

    public void setFlagAvailiable(boolean flagAvailiable) {
        this.flagAvailiable = flagAvailiable;
    }

}
