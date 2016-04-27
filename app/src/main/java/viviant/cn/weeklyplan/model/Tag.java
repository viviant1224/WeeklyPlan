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
public class Tag implements Serializable{
    private int id;
    private String tagName;
    private int state;
    private String establishDate;
    private boolean flagAvailiable;
    private String tagDescription;

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

    public String getTagDescription() {
        return tagDescription;
    }

    public void setTagDescription(String tagDescription) {
        this.tagDescription = tagDescription;
    }

    public boolean isFlagAvailiable() {
        return flagAvailiable;
    }

    public void setFlagAvailiable(boolean flagAvailiable) {
        this.flagAvailiable = flagAvailiable;
    }

}
