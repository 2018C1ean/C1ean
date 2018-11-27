package com.example.dell.c1ean.DAO;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.dell.c1ean.Bean.Worker;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "WORKER".
*/
public class WorkerDao extends AbstractDao<Worker, Long> {

    public static final String TABLENAME = "WORKER";

    /**
     * Properties of entity Worker.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Img = new Property(2, String.class, "img", false, "IMG");
        public final static Property Type = new Property(3, String.class, "type", false, "TYPE");
        public final static Property Password = new Property(4, String.class, "password", false, "PASSWORD");
        public final static Property Tel = new Property(5, String.class, "tel", false, "TEL");
        public final static Property Company_id = new Property(6, int.class, "company_id", false, "COMPANY_ID");
        public final static Property Introduction = new Property(7, String.class, "introduction", false, "INTRODUCTION");
        public final static Property Start = new Property(8, String.class, "start", false, "START");
    }


    public WorkerDao(DaoConfig config) {
        super(config);
    }
    
    public WorkerDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"WORKER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + // 0: id
                "\"NAME\" TEXT NOT NULL ," + // 1: name
                "\"IMG\" TEXT," + // 2: img
                "\"TYPE\" TEXT NOT NULL ," + // 3: type
                "\"PASSWORD\" TEXT NOT NULL ," + // 4: password
                "\"TEL\" TEXT NOT NULL ," + // 5: tel
                "\"COMPANY_ID\" INTEGER NOT NULL ," + // 6: company_id
                "\"INTRODUCTION\" TEXT," + // 7: introduction
                "\"START\" TEXT);"); // 8: start
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"WORKER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Worker entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindString(2, entity.getName());
 
        String img = entity.getImg();
        if (img != null) {
            stmt.bindString(3, img);
        }
        stmt.bindString(4, entity.getType());
        stmt.bindString(5, entity.getPassword());
        stmt.bindString(6, entity.getTel());
        stmt.bindLong(7, entity.getCompany_id());
 
        String introduction = entity.getIntroduction();
        if (introduction != null) {
            stmt.bindString(8, introduction);
        }
 
        String start = entity.getStart();
        if (start != null) {
            stmt.bindString(9, start);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Worker entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindString(2, entity.getName());
 
        String img = entity.getImg();
        if (img != null) {
            stmt.bindString(3, img);
        }
        stmt.bindString(4, entity.getType());
        stmt.bindString(5, entity.getPassword());
        stmt.bindString(6, entity.getTel());
        stmt.bindLong(7, entity.getCompany_id());
 
        String introduction = entity.getIntroduction();
        if (introduction != null) {
            stmt.bindString(8, introduction);
        }
 
        String start = entity.getStart();
        if (start != null) {
            stmt.bindString(9, start);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public Worker readEntity(Cursor cursor, int offset) {
        Worker entity = new Worker( //
            cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // img
            cursor.getString(offset + 3), // type
            cursor.getString(offset + 4), // password
            cursor.getString(offset + 5), // tel
            cursor.getInt(offset + 6), // company_id
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // introduction
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // start
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Worker entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setName(cursor.getString(offset + 1));
        entity.setImg(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setType(cursor.getString(offset + 3));
        entity.setPassword(cursor.getString(offset + 4));
        entity.setTel(cursor.getString(offset + 5));
        entity.setCompany_id(cursor.getInt(offset + 6));
        entity.setIntroduction(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setStart(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Worker entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Worker entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Worker entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
