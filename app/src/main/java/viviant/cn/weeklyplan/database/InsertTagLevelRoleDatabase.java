package viviant.cn.weeklyplan.database;

import viviant.cn.weeklyplan.bean.Level;
import viviant.cn.weeklyplan.bean.Role;
import viviant.cn.weeklyplan.bean.Tag;
import viviant.cn.weeklyplan.bean.Userinfo;
import viviant.cn.weeklyplan.db.LevelDBManager;
import viviant.cn.weeklyplan.db.RoleDBManager;
import viviant.cn.weeklyplan.db.TagDBManager;
import viviant.cn.weeklyplan.db.UserinfoDBManager;
import viviant.cn.weeklyplan.util.DateUtil;

/**
 * Created by weiwei.huang on 16-5-11.
 */
public class InsertTagLevelRoleDatabase {

    public static void initDatabase() {
        //insert level,role,tag

        insertUserinfo();
        insertLevel();
        insertRole();
        insertTag();

    }

    private static void insertUserinfo() {
        Userinfo userinfo = new Userinfo();
        userinfo.setFlagVip(false);
        userinfo.setFlagLogout(false);
        userinfo.setState(0);
        userinfo.setUsername("viviant");
        userinfo.setAddress("fujian putian");
        userinfo.setRegisterDate(DateUtil.getCurrentTime());
        userinfo.setSex("男");
        userinfo.setTelphone("17845125874");
        userinfo.setUserEmail("18686672327@163.com");
        new UserinfoDBManager().insert(userinfo);
    }

    private static void insertTag() {
        Tag tag1 = new Tag();
        tag1.setState(0);
        tag1.setTagName("learning");
        tag1.setFlagAvailiable(true);
        tag1.setEstablishDate(DateUtil.getCurrentTime());
        tag1.setUserinfoTId(1);
        tag1.setTagDescription("learning");
        new TagDBManager().insert(tag1);


        Tag tag2 = new Tag();
        tag2.setState(0);
        tag2.setTagName("ball");
        tag2.setFlagAvailiable(true);
        tag2.setEstablishDate(DateUtil.getCurrentTime());
        tag2.setUserinfoTId(1);
        tag2.setTagDescription("ball");
        new TagDBManager().insert(tag2);




    }


    private static void insertRole() {
        Role role1 = new Role();
        role1.setState(0);
        role1.setFlagAvailiable(true);
        role1.setEstablishDate(DateUtil.getCurrentTime());
        role1.setRoleDescription("student");
        role1.setRoleName("student");
        role1.setUserinfoRId(1);
        new RoleDBManager().insert(role1);


        Role role2 = new Role();
        role2.setState(0);
        role2.setFlagAvailiable(true);
        role2.setEstablishDate(DateUtil.getCurrentTime());
        role2.setRoleDescription("worker");
        role2.setRoleName("worker");
        role2.setUserinfoRId(1);
        new RoleDBManager().insert(role2);

        Role role3 = new Role();
        role3.setState(0);
        role3.setFlagAvailiable(true);
        role3.setEstablishDate(DateUtil.getCurrentTime());
        role3.setRoleDescription("friend");
        role3.setRoleName("friend");
        role3.setUserinfoRId(1);
        new RoleDBManager().insert(role3);

        Role role4 = new Role();
        role4.setState(0);
        role4.setFlagAvailiable(true);
        role4.setEstablishDate(DateUtil.getCurrentTime());
        role4.setRoleDescription("boss");
        role4.setRoleName("boss");
        role4.setUserinfoRId(1);
        new RoleDBManager().insert(role4);

    }

    private static void insertLevel() {
        Level level1 = new Level();
        level1.setState(0);
        level1.setColor("blue");
        level1.setLevelDescription("正常级别");
        level1.setLevelName("正常级别");
        level1.setLevelNum(2);
        new LevelDBManager().insert(level1);

        Level level2 = new Level();
        level2.setState(0);
        level2.setColor("orange");
        level2.setLevelDescription("不紧急不重要");
        level2.setLevelName("不紧急不重要");
        level2.setLevelNum(1);
        new LevelDBManager().insert(level2);

        Level level3 = new Level();
        level3.setState(0);
        level3.setColor("violet");//紫色
        level3.setLevelDescription("紧急不重要");
        level3.setLevelName("紧急不重要");
        level3.setLevelNum(4);
        new LevelDBManager().insert(level3);

        Level level4 = new Level();
        level4.setState(0);
        level4.setColor("violet");
        level4.setLevelDescription("重要不紧急");
        level4.setLevelName("重要不紧急");
        level4.setLevelNum(4);
        new LevelDBManager().insert(level4);

        Level level5 = new Level();
        level5.setState(0);
        level5.setColor("red");
        level5.setLevelDescription("紧急且重要");
        level5.setLevelName("紧急且重要");
        level5.setLevelNum(5);
        new LevelDBManager().insert(level5);
    }
}
