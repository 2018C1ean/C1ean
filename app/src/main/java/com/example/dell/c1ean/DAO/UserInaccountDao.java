package com.example.dell.c1ean.DAO;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.dell.c1ean.Bean.UserInaccount;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_INACCOUNT".
*/
public class UserInaccountDao extends AbstractDao<UserInaccount, Long> {

    public static final String TABLENAME = "USER_INACCOUNT";

    /**
     * Properties of entity UserInaccount.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property User_id = new Property(0, Long.class, "user_id", true, "_id");
        public final static Property Time = new Property(1, String.class, "time", false, "TIME");
        public final static Property Money = new Property(2, float.class, "money", false, "MONEY");
        public final static Property Payer = new Property(3, String.class, "Payer", false, "PAYER");
    }


    public UserInaccountDao(DaoConfig config) {
        super(config);
    }
    
    public UserInaccountDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_INACCOUNT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: user_id
                "\"TIME\" TEXT NOT NULL ," + // 1: time
                "\"MONEY\" REAL NOT NULL ," + // 2: money
                "\"PAYER\" TEXT NOT NULL );"); // 3: Payer
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_INACCOUNT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserInaccount entity) {
        stmt.clearBindings();
 
        Long user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindLong(1, user_id);
        }
        stmt.bindString(2, entity.getTime());
        stmt.bindDouble(3, entity.getMoney());
        stmt.bindString(4, entity.getPayer());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserInaccount entity) {
        stmt.clearBindings();
 
        Long user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindLong(1, user_id);
        }
        stmt.bindString(2, entity.getTime());
        stmt.bindDouble(3, entity.getMoney());
        stmt.bindString(4, entity.getPayer());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public UserInaccount readEntity(Cursor cursor, int offset) {
        UserInaccount entity = new UserInaccount( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // user_id
            cursor.getString(offset + 1), // time
            cursor.getFloat(offset + 2), // money
            cursor.getString(offset + 3) // Payer
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserInaccount entity, int offset) {
        entity.setUser_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTime(cursor.getString(offset + 1));
        entity.setMoney(cursor.getFloat(offset + 2));
        entity.setPayer(cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserInaccount entity, long rowId) {
        entity.setUser_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserInaccount entity) {
        if(entity != null) {
            return entity.getUser_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserInaccount entity) {
        return entity.getUser_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
