package com.cy.util;

import com.cy.app.UtilContext;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;

import static com.litesuits.orm.samples.CascadeTestActivity.liteOrm;


/**
 * Created by admin on 2017/1/18.
 */

public class UtilLiteOrm {
    private volatile static LiteOrm instance;

    public static LiteOrm getDB(){

        if (instance == null) {
            synchronized (LiteOrm.class) {
                if (instance == null) {
                    // 使用级联操作
//            DataBaseConfig config = new DataBaseConfig(this, DB_NAME);
                    DataBaseConfig config = new DataBaseConfig(UtilContext.getContext(), "liteorm_cascade.db");
                    config.debugged = true; // open the log
                    config.dbVersion = 1; // set database version
                    config.onUpdateListener = null; // set database update listener
                    instance = LiteOrm.newCascadeInstance(config);// cascade
                }
            }
        }
        return instance;
    }
}
