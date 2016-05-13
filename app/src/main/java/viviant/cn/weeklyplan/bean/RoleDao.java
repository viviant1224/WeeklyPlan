package viviant.cn.weeklyplan.bean;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import viviant.cn.weeklyplan.bean.Role;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table ROLE.
*/
public class RoleDao extends AbstractDao<Role, Long> {

    public static final String TABLENAME = "ROLE";

    /**
     * Properties of entity Role.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property RoleName = new Property(1, String.class, "roleName", false, "ROLE_NAME");
        public final static Property EstablishDate = new Property(2, String.class, "establishDate", false, "ESTABLISH_DATE");
        public final static Property RoleDescription = new Property(3, String.class, "roleDescription", false, "ROLE_DESCRIPTION");
        public final static Property FlagAvailiable = new Property(4, Boolean.class, "flagAvailiable", false, "FLAG_AVAILIABLE");
        public final static Property State = new Property(5, Integer.class, "state", false, "STATE");
        public final static Property UserinfoRId = new Property(6, long.class, "userinfoRId", false, "USERINFO_RID");
    };

    private DaoSession daoSession;

    private Query<Role> userinfo_RolesQuery;

    public RoleDao(DaoConfig config) {
        super(config);
    }
    
    public RoleDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'ROLE' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'ROLE_NAME' TEXT NOT NULL ," + // 1: roleName
                "'ESTABLISH_DATE' TEXT NOT NULL ," + // 2: establishDate
                "'ROLE_DESCRIPTION' TEXT," + // 3: roleDescription
                "'FLAG_AVAILIABLE' INTEGER," + // 4: flagAvailiable
                "'STATE' INTEGER," + // 5: state
                "'USERINFO_RID' INTEGER NOT NULL );"); // 6: userinfoRId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'ROLE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Role entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getRoleName());
        stmt.bindString(3, entity.getEstablishDate());
 
        String roleDescription = entity.getRoleDescription();
        if (roleDescription != null) {
            stmt.bindString(4, roleDescription);
        }
 
        Boolean flagAvailiable = entity.getFlagAvailiable();
        if (flagAvailiable != null) {
            stmt.bindLong(5, flagAvailiable ? 1l: 0l);
        }
 
        Integer state = entity.getState();
        if (state != null) {
            stmt.bindLong(6, state);
        }
        stmt.bindLong(7, entity.getUserinfoRId());
    }

    @Override
    protected void attachEntity(Role entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Role readEntity(Cursor cursor, int offset) {
        Role entity = new Role( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // roleName
            cursor.getString(offset + 2), // establishDate
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // roleDescription
            cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0, // flagAvailiable
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // state
            cursor.getLong(offset + 6) // userinfoRId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Role entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setRoleName(cursor.getString(offset + 1));
        entity.setEstablishDate(cursor.getString(offset + 2));
        entity.setRoleDescription(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setFlagAvailiable(cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0);
        entity.setState(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setUserinfoRId(cursor.getLong(offset + 6));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Role entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Role entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "roles" to-many relationship of Userinfo. */
    public List<Role> _queryUserinfo_Roles(long userinfoRId) {
        synchronized (this) {
            if (userinfo_RolesQuery == null) {
                QueryBuilder<Role> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.UserinfoRId.eq(null));
                userinfo_RolesQuery = queryBuilder.build();
            }
        }
        Query<Role> query = userinfo_RolesQuery.forCurrentThread();
        query.setParameter(0, userinfoRId);
        return query.list();
    }

}