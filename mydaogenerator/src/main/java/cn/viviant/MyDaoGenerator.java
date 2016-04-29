package cn.viviant;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class MyDaoGenerator {
    public static void main(String[] args) throws Exception {
        // 正如你所见的，你创建了一个用于添加实体（Entity）的模式（Schema）对象。
        // 两个参数分别代表：数据库版本号与自动生成代码的包路径。
        Schema schema = new Schema(1, "viviant.cn.weeklyplan.bean");
//      当然，如果你愿意，你也可以分别指定生成的 Bean 与 DAO 类所在的目录，只要如下所示：
//      Schema schema = new Schema(1, "me.itangqi.bean");
//      schema.setDefaultJavaPackageDao("me.itangqi.dao");

        // 模式（Schema）同时也拥有两个默认的 flags，分别用来标示 entity 是否是 activie 以及是否使用 keep sections。
        // schema2.enableActiveEntitiesByDefault();
        // schema2.enableKeepSectionsByDefault();

        // 一旦你拥有了一个 Schema 对象后，你便可以使用它添加实体（Entities）了。
//        addLevel(schema);
//
//        addPlanthing(schema);
//
//        addRole(schema);
//
//        addTag(schema);
//
//        addUserinfo(schema);
        initDatabases(schema);

        // 最后我们将使用 DAOGenerator 类的 generateAll() 方法自动生成代码，此处你需要根据自己的情况更改输出目录（既之前创建的 java-gen)。
        // 其实，输出目录的路径可以在 build.gradle 中设置，有兴趣的朋友可以自行搜索，这里就不再详解。
        new DaoGenerator().generateAll(schema, "../WeeklyPlan/app/src/main/java");
    }

    /**
     *
     * @param schema
     */
    private static void addTag(Schema schema) {
        Entity tag = schema.addEntity("Tag");
        // 你也可以重新给表命名
        tag.setTableName("TAG");

        // greenDAO 会自动根据实体类的属性值来创建表字段，并赋予默认值
        // 接下来你便可以设置表中的字段：
        tag.addIdProperty();
        tag.addStringProperty("tagName").notNull();
        tag.addStringProperty("establishDate").notNull();
        tag.addStringProperty("tagDescription");
        tag.addBooleanProperty("flagAvailiable");
        tag.addIntProperty("state");
    }

    /**
     *
     * @param schema
     */
    private static void addRole(Schema schema) {
        Entity role = schema.addEntity("Role");
        // 你也可以重新给表命名
        role.setTableName("ROLE");

        // greenDAO 会自动根据实体类的属性值来创建表字段，并赋予默认值
        // 接下来你便可以设置表中的字段：
        role.addIdProperty();
        role.addStringProperty("roleName").notNull();
        role.addStringProperty("establishDate").notNull();
        role.addStringProperty("roleDescription");
        role.addBooleanProperty("flagAvailiable");
        role.addIntProperty("state");
    }

    /**
     *
     * @param schema
     */
    private static void addLevel(Schema schema) {

        Entity level = schema.addEntity("Level");
        // 你也可以重新给表命名
        level.setTableName("LEVEL");

        // greenDAO 会自动根据实体类的属性值来创建表字段，并赋予默认值
        // 接下来你便可以设置表中的字段：
        level.addIdProperty();
        level.addStringProperty("levelName").notNull();
        level.addStringProperty("levelDescription").notNull();
        level.addStringProperty("color");
        level.addIntProperty("levelNum");
        level.addIntProperty("state");
    }


    /**
     * @param schema
     */
    private static void addPlanthing(Schema schema) {
        // 一个实体（类）就关联到数据库中的一张表，此处表名为「Note」（既类名）
        Entity planthing = schema.addEntity("Planthing");
        // 你也可以重新给表命名
        planthing.setTableName("PLANTHING");

        // greenDAO 会自动根据实体类的属性值来创建表字段，并赋予默认值
        // 接下来你便可以设置表中的字段：
        planthing.addIdProperty();
        planthing.addStringProperty("planthingName").notNull();
        planthing.addStringProperty("planthingDescription");
        planthing.addStringProperty("doDateTime").notNull();
        planthing.addStringProperty("planThingPlace");
        planthing.addBooleanProperty("flagRemind");
        planthing.addStringProperty("remindDate");
        planthing.addIntProperty("state");
        // 与在 Java 中使用驼峰命名法不同，默认数据库中的命名是使用大写和下划线来分割单词的。
        // For example, a property called “creationDate” will become a database column “CREATION_DATE”.
    }

    /**
     * @param schema
     */
    private static void addUserinfo(Schema schema) {

        // 一个实体（类）就关联到数据库中的一张表，此处表名为「Note」（既类名）
        Entity userinfo = schema.addEntity("Userinfo");
        // 你也可以重新给表命名
        userinfo.setTableName("USERINFO");
        userinfo.addIdProperty();
        userinfo.addStringProperty("username").notNull();
        userinfo.addStringProperty("sex");
        userinfo.addStringProperty("userEmail");
        userinfo.addStringProperty("userImage");
        userinfo.addStringProperty("registerDate");
        userinfo.addBooleanProperty("flagVip");
        userinfo.addBooleanProperty("flagLogout");
        userinfo.addIntProperty("state");
        userinfo.addStringProperty("telphone");
        userinfo.addStringProperty("address");
    }


    private static void initDatabases(Schema schema) {

        // 一个实体（类）就关联到数据库中的一张表，此处表名为「Note」（既类名）
        Entity userinfo = schema.addEntity("Userinfo");
        // 你也可以重新给表命名
        userinfo.setTableName("USERINFO");
        userinfo.addIdProperty();
        userinfo.addStringProperty("username").notNull();
        userinfo.addStringProperty("sex");
        userinfo.addStringProperty("userEmail");
        userinfo.addStringProperty("userImage");
        userinfo.addStringProperty("registerDate");
        userinfo.addBooleanProperty("flagVip");
        userinfo.addBooleanProperty("flagLogout");
        userinfo.addIntProperty("state");
        userinfo.addStringProperty("telphone");
        userinfo.addStringProperty("address");

        //Tag
        Entity tag = schema.addEntity("Tag");
        // 你也可以重新给表命名
        tag.setTableName("TAG");

        // greenDAO 会自动根据实体类的属性值来创建表字段，并赋予默认值
        // 接下来你便可以设置表中的字段：
        tag.addIdProperty();
        tag.addStringProperty("tagName").notNull();
        tag.addStringProperty("establishDate").notNull();
        tag.addStringProperty("tagDescription");
        tag.addBooleanProperty("flagAvailiable");
        tag.addIntProperty("state");

        //Role
        Entity role = schema.addEntity("Role");
        // 你也可以重新给表命名
        role.setTableName("ROLE");

        // greenDAO 会自动根据实体类的属性值来创建表字段，并赋予默认值
        // 接下来你便可以设置表中的字段：
        role.addIdProperty();
        role.addStringProperty("roleName").notNull();
        role.addStringProperty("establishDate").notNull();
        role.addStringProperty("roleDescription");
        role.addBooleanProperty("flagAvailiable");
        role.addIntProperty("state");

        //Level
        Entity level = schema.addEntity("Level");
        // 你也可以重新给表命名
        level.setTableName("LEVEL");

        // greenDAO 会自动根据实体类的属性值来创建表字段，并赋予默认值
        // 接下来你便可以设置表中的字段：
        level.addIdProperty();
        level.addStringProperty("levelName").notNull();
        level.addStringProperty("levelDescription").notNull();
        level.addStringProperty("color");
        level.addIntProperty("levelNum");
        level.addIntProperty("state");

        //Planthing
        // 一个实体（类）就关联到数据库中的一张表，此处表名为「Note」（既类名）
        Entity planthing = schema.addEntity("Planthing");
        // 你也可以重新给表命名
        planthing.setTableName("PLANTHING");

        // greenDAO 会自动根据实体类的属性值来创建表字段，并赋予默认值
        // 接下来你便可以设置表中的字段：
        planthing.addIdProperty();
        planthing.addStringProperty("planthingName").notNull();
        planthing.addStringProperty("planthingDescription");
        planthing.addStringProperty("doDateTime").notNull();
        planthing.addStringProperty("planThingPlace");
        planthing.addBooleanProperty("flagRemind");
        planthing.addStringProperty("remindDate");
        planthing.addIntProperty("state");


        //add relations

        Property levelId = planthing.addLongProperty("levelId").notNull().getProperty();
        ToMany levelToPlanthings = level.addToMany(planthing, levelId);
        levelToPlanthings.setName("planthings"); // Optional

        Property tagId = planthing.addLongProperty("tagId").notNull().getProperty();
        ToMany tagToPlanthings = tag.addToMany(planthing, tagId);
        tagToPlanthings.setName("planthings"); // Optional

        Property roleId = planthing.addLongProperty("roleId").notNull().getProperty();
        ToMany roleToPlanthings = role.addToMany(planthing, roleId);
        roleToPlanthings.setName("planthings"); // Optional

        Property userinfoPId = planthing.addLongProperty("userinfoPId").notNull().getProperty();
        ToMany userinfoToPlanthings = userinfo.addToMany(planthing, userinfoPId);
        userinfoToPlanthings.setName("planthings"); // Optional



        Property userinfoRId = role.addLongProperty("userinfoRId").notNull().getProperty();
        ToMany userinfoToRoles = userinfo.addToMany(role, userinfoRId);
        userinfoToRoles.setName("roles"); // Optional

        Property userinfoTId = tag.addLongProperty("userinfoTId").notNull().getProperty();
        ToMany userinfoToTags = userinfo.addToMany(tag, userinfoTId);
        userinfoToTags.setName("tags"); // Optional




    }


}
