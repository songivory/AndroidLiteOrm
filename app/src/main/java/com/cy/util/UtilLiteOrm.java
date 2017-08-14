package com.cy.util;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;
import com.litesuits.orm.db.assit.SQLiteHelper;


/**
 * Created by admin on 2017/1/18.
 */

public class UtilLiteOrm {
    private volatile static LiteOrm instance;
    private static final String DB_PREFIX="liteOrm_";
    private boolean mIsDebug=true;
    private int mDbVersion=1;
    private Context mContext;
    private SQLiteHelper.OnUpdateListener mOnUpdateListener;
    private String mDbName;

    public String getDbName() {
        return mDbName;
    }

    public UtilLiteOrm setDbName(String dbName) {
        mDbName = dbName;
        return this;
    }

    public UtilLiteOrm(Context context){
        mContext=context;
    }

    public boolean isDebug() {
        return mIsDebug;
    }

    public UtilLiteOrm setDebug(boolean debug) {
        mIsDebug = debug;
        return this;
    }

    public int getDbVersion() {
        return mDbVersion;
    }

    public UtilLiteOrm setDbVersion(int dbVersion) {
        mDbVersion = dbVersion;
        return this;
    }

    public SQLiteHelper.OnUpdateListener getOnUpdateListener() {
        return mOnUpdateListener;
    }

    public UtilLiteOrm setOnUpdateListener(SQLiteHelper.OnUpdateListener onUpdateListener) {
        mOnUpdateListener = onUpdateListener;
        return this;
    }

    public LiteOrm getDB(){
        if (instance == null) {
            synchronized (LiteOrm.class) {
                if (instance == null) {
                    // 使用级联操作
//            DataBaseConfig config = new DataBaseConfig(this, DB_NAME);
                    DataBaseConfig config = new DataBaseConfig(mContext, mDbName.endsWith(".db")?mDbName: (mDbName+".db"));
                    config.debugged = mIsDebug; // open the log
                    config.dbVersion = mDbVersion; // set database version
                    config.onUpdateListener = mOnUpdateListener; // set database update listener
                    instance = LiteOrm.newCascadeInstance(config);// cascade
                }
            }
        }
        return instance;
    }

    public void close(){
        getDB().close();
        instance=null;
    }
    private void testSave(Object obj) {
        getDB().save(obj);
    }
}
