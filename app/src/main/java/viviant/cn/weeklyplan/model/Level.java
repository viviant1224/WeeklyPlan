package viviant.cn.weeklyplan.model;

import java.io.Serializable;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: viviant
 * Date: 15-4-30
 * Time: 下午1:55
 * To change this template use File | Settings | File Templates.
 */
public class Level implements Serializable{
    //级别分类：默认3 重要5 警告2 信息 1 危险 4 来自bootstrap
    private int id;
    private String levelDescription;
    private String levelName;
    private String color;
    private int levelNum;
    private int state;
    private Set<Planthing> planthingSet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevelDescription() {
        return levelDescription;
    }

    public void setLevelDescription(String levelDescription) {
        this.levelDescription = levelDescription;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getLevelNum() {
        return levelNum;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setLevelNum(int levelNum) {
        this.levelNum = levelNum;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Set<Planthing> getPlanthingSet() {
        return planthingSet;
    }

    public void setPlanthingSet(Set<Planthing> planthingSet) {
        this.planthingSet = planthingSet;
    }
}
