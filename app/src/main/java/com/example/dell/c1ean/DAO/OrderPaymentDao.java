package com.example.dell.c1ean.DAO;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.dell.c1ean.Bean.Order;
import com.example.dell.c1ean.Bean.Worker;

import com.example.dell.c1ean.Bean.OrderPayment;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ORDER_PAYMENT".
*/
public class OrderPaymentDao extends AbstractDao<OrderPayment, Long> {

    public static final String TABLENAME = "ORDER_PAYMENT";

    /**
     * Properties of entity OrderPayment.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Order_id = new Property(0, Long.class, "order_id", true, "_id");
        public final static Property Order_money = new Property(1, float.class, "order_money", false, "ORDER_MONEY");
        public final static Property Worker_id = new Property(2, long.class, "worker_id", false, "WORKER_ID");
        public final static Property Worker_money = new Property(3, float.class, "worker_money", false, "WORKER_MONEY");
        public final static Property Company_id = new Property(4, long.class, "company_id", false, "COMPANY_ID");
        public final static Property Company_money = new Property(5, float.class, "company_money", false, "COMPANY_MONEY");
        public final static Property Order_time = new Property(6, String.class, "order_time", false, "ORDER_TIME");
        public final static Property Paytoworker_time = new Property(7, String.class, "paytoworker_time", false, "PAYTOWORKER_TIME");
        public final static Property Paytocompany_time = new Property(8, String.class, "paytocompany_time", false, "PAYTOCOMPANY_TIME");
    }

    private DaoSession daoSession;


    public OrderPaymentDao(DaoConfig config) {
        super(config);
    }
    
    public OrderPaymentDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ORDER_PAYMENT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: order_id
                "\"ORDER_MONEY\" REAL NOT NULL ," + // 1: order_money
                "\"WORKER_ID\" INTEGER NOT NULL ," + // 2: worker_id
                "\"WORKER_MONEY\" REAL NOT NULL ," + // 3: worker_money
                "\"COMPANY_ID\" INTEGER NOT NULL ," + // 4: company_id
                "\"COMPANY_MONEY\" REAL NOT NULL ," + // 5: company_money
                "\"ORDER_TIME\" TEXT NOT NULL ," + // 6: order_time
                "\"PAYTOWORKER_TIME\" TEXT," + // 7: paytoworker_time
                "\"PAYTOCOMPANY_TIME\" TEXT);"); // 8: paytocompany_time
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ORDER_PAYMENT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, OrderPayment entity) {
        stmt.clearBindings();
 
        Long order_id = entity.getOrder_id();
        if (order_id != null) {
            stmt.bindLong(1, order_id);
        }
        stmt.bindDouble(2, entity.getOrder_money());
        stmt.bindLong(3, entity.getWorker_id());
        stmt.bindDouble(4, entity.getWorker_money());
        stmt.bindLong(5, entity.getCompany_id());
        stmt.bindDouble(6, entity.getCompany_money());
        stmt.bindString(7, entity.getOrder_time());
 
        String paytoworker_time = entity.getPaytoworker_time();
        if (paytoworker_time != null) {
            stmt.bindString(8, paytoworker_time);
        }
 
        String paytocompany_time = entity.getPaytocompany_time();
        if (paytocompany_time != null) {
            stmt.bindString(9, paytocompany_time);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, OrderPayment entity) {
        stmt.clearBindings();
 
        Long order_id = entity.getOrder_id();
        if (order_id != null) {
            stmt.bindLong(1, order_id);
        }
        stmt.bindDouble(2, entity.getOrder_money());
        stmt.bindLong(3, entity.getWorker_id());
        stmt.bindDouble(4, entity.getWorker_money());
        stmt.bindLong(5, entity.getCompany_id());
        stmt.bindDouble(6, entity.getCompany_money());
        stmt.bindString(7, entity.getOrder_time());
 
        String paytoworker_time = entity.getPaytoworker_time();
        if (paytoworker_time != null) {
            stmt.bindString(8, paytoworker_time);
        }
 
        String paytocompany_time = entity.getPaytocompany_time();
        if (paytocompany_time != null) {
            stmt.bindString(9, paytocompany_time);
        }
    }

    @Override
    protected final void attachEntity(OrderPayment entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public OrderPayment readEntity(Cursor cursor, int offset) {
        OrderPayment entity = new OrderPayment( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // order_id
            cursor.getFloat(offset + 1), // order_money
            cursor.getLong(offset + 2), // worker_id
            cursor.getFloat(offset + 3), // worker_money
            cursor.getLong(offset + 4), // company_id
            cursor.getFloat(offset + 5), // company_money
            cursor.getString(offset + 6), // order_time
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // paytoworker_time
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // paytocompany_time
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, OrderPayment entity, int offset) {
        entity.setOrder_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setOrder_money(cursor.getFloat(offset + 1));
        entity.setWorker_id(cursor.getLong(offset + 2));
        entity.setWorker_money(cursor.getFloat(offset + 3));
        entity.setCompany_id(cursor.getLong(offset + 4));
        entity.setCompany_money(cursor.getFloat(offset + 5));
        entity.setOrder_time(cursor.getString(offset + 6));
        entity.setPaytoworker_time(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setPaytocompany_time(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(OrderPayment entity, long rowId) {
        entity.setOrder_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(OrderPayment entity) {
        if(entity != null) {
            return entity.getOrder_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(OrderPayment entity) {
        return entity.getOrder_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getOrderDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getWorkerDao().getAllColumns());
            builder.append(" FROM ORDER_PAYMENT T");
            builder.append(" LEFT JOIN ORDER T0 ON T.\"_id\"=T0.\"_id\"");
            builder.append(" LEFT JOIN WORKER T1 ON T.\"WORKER_ID\"=T1.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected OrderPayment loadCurrentDeep(Cursor cursor, boolean lock) {
        OrderPayment entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Order order = loadCurrentOther(daoSession.getOrderDao(), cursor, offset);
        entity.setOrder(order);
        offset += daoSession.getOrderDao().getAllColumns().length;

        Worker worker = loadCurrentOther(daoSession.getWorkerDao(), cursor, offset);
         if(worker != null) {
            entity.setWorker(worker);
        }

        return entity;    
    }

    public OrderPayment loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<OrderPayment> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<OrderPayment> list = new ArrayList<OrderPayment>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<OrderPayment> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<OrderPayment> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
