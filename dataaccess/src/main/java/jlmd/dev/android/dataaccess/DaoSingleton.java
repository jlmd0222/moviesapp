package jlmd.dev.android.dataaccess;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DaoSingleton {
    private static DaoMaster.DevOpenHelper _openHelper;
    private static DaoMaster _daoMaster;
    private static DaoSession _daoSession;
    private static SQLiteDatabase _db;

    public DaoSession getDaoSession() {
        return _daoSession;
    }

    public void setDaoSession(DaoSession daoSession) {
        _daoSession = daoSession;
    }

    public DaoMaster getDaoMaster() {
        return _daoMaster;
    }

    public void setDaoMaster(DaoMaster daoMaster) {
        _daoMaster = daoMaster;
    }

    public DaoMaster.DevOpenHelper getOpenHelper() {
        return _openHelper;
    }

    public void setOpenHelper(DaoMaster.DevOpenHelper openHelper) {
        _openHelper = openHelper;
    }

    public static SQLiteDatabase get_db() {
        return _db;
    }

    public static void set_db(SQLiteDatabase _db) {
        DaoSingleton._db = _db;
    }

    private static DaoSingleton mInstance = null;

    public DaoSingleton(Context context) throws Exception {
        _openHelper = new DaoMaster.DevOpenHelper(context, "databasedb");
        _db = _openHelper.getWritableDatabase();
        _daoMaster = new DaoMaster(_db);
        _daoSession = _daoMaster.newSession();
    }

    public DaoSingleton(Context context, String name) throws Exception {
        _openHelper = new DaoMaster.DevOpenHelper(context, name);
        _db = _openHelper.getWritableDatabase();
        _daoMaster = new DaoMaster(_db);
        _daoSession = _daoMaster.newSession();
    }

    public static void closeDb() {
        _db.close();
        _db = null;
    }

    public static DaoSingleton getInstance(Context context) {
        try {
            if (_db == null) {
                if (_openHelper == null)
                    _openHelper = new DaoMaster.DevOpenHelper(context, "databasedb");

                _db = _openHelper.getWritableDatabase();
                _daoMaster = new DaoMaster(_db);
                _daoSession = _daoMaster.newSession();
            }

            if (mInstance == null) {
                mInstance = new DaoSingleton(context);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return mInstance;
    }

    public static DaoSingleton getInstanceOriginalDB(Context context)    {
        try {
            if (_db == null) {
                _openHelper = new DaoMaster.DevOpenHelper(context, "OriginalDB");

                _db = _openHelper.getWritableDatabase();
                _daoMaster = new DaoMaster(_db);
                _daoSession = _daoMaster.newSession();
            }

            mInstance = new DaoSingleton(context,"OriginalDB");
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return mInstance;
    }
}
