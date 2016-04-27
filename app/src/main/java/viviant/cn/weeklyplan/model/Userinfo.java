package viviant.cn.weeklyplan.model;

import java.io.Serializable;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: viviant
 * Date: 15-4-30
 * Time: 下午1:38
 * To change this template use File | Settings | File Templates.
 */
public class Userinfo implements Serializable{

    private static final long serialVersionUID = -3503516717489584949L;
    private int id;
    private String username;
    private String sex;
    private String userEmail;
    private String userImage;
    private String registerDate;
    private boolean flagVip;
    private boolean flagLogout;
    private int state;
    private String telphone;
    private String address;
    private String password;
    private Set<Plan> planSet;    //用户的计划
    private Set<UserRole> userRoleSet;
    private Set<UserTag> userTagSet;
    private Set<Userinfo> friendSet;
    private Set<Planthing> planthingSet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public boolean isFlagVip() {
        return flagVip;
    }

    public void setFlagVip(boolean flagVip) {
        this.flagVip = flagVip;
    }

    public boolean isFlagLogout() {
        return flagLogout;
    }

    public void setFlagLogout(boolean flagLogout) {
        this.flagLogout = flagLogout;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Plan> getPlanSet() {
        return planSet;
    }

    public void setPlanSet(Set<Plan> planSet) {
        this.planSet = planSet;
    }

    public Set<UserRole> getUserRoleSet() {
        return userRoleSet;
    }

    public void setUserRoleSet(Set<UserRole> userRoleSet) {
        this.userRoleSet = userRoleSet;
    }

    public Set<UserTag> getUserTagSet() {
        return userTagSet;
    }

    public void setUserTagSet(Set<UserTag> userTagSet) {
        this.userTagSet = userTagSet;
    }

    public Set<Userinfo> getFriendSet() {
        return friendSet;
    }

    public void setFriendSet(Set<Userinfo> friendSet) {
        this.friendSet = friendSet;
    }

    public Set<Planthing> getPlanthingSet() {
        return planthingSet;
    }

    public void setPlanthingSet(Set<Planthing> planthingSet) {
        this.planthingSet = planthingSet;
    }
}
