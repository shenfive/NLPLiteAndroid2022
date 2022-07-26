package idv.sjw.nlpfree;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

public class DBManager {


    private SQLiteDatabase sqlDB;

    static final String DBName = "nlpdb";
    static final String TableName = "qustionList";

    static final String ColKey = "key";
    static final String ColQustion = "qustion";
    static final String ColA = "a";
    static final String ColB = "b";
    static final String ColC = "c";


    static final int DBVersion = 1 ;

    static final String CreadteDB = "CREATE TABLE IF NOT EXISTS \'" + TableName + "\'(" +
            "\'"+ColKey+"\' INTEGER,"+
            "\'"+ColQustion+"\' TEXT,"+
            "\'"+ColA+"\' TEXT,"+
            "\'"+ColB+"\' TEXT,"+
            "\'"+ColC+"\' TEXT,"+
            "PRIMARY KEY (\'key\')" +
            ")";


    static class DatabaseHelperUser extends SQLiteOpenHelper {
        Context context;
        DatabaseHelperUser(Context context){
            super(context,DBName,null,DBVersion);
            this.context = context;
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CreadteDB);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS" + TableName);
            onCreate(db);
        }
    }

    public long insertData(ContentValues contentValues){
        long ID = sqlDB.insert(TableName,"",contentValues);
        return ID;
    }


    //select qustion,a,b,c form Table
    public Cursor query(String[] projection,String  selection,String[] selectionArgs, String sortOrder){
        SQLiteQueryBuilder sqLiteQuery = new SQLiteQueryBuilder();
        sqLiteQuery.setTables(TableName);
        Cursor cursor;
        cursor = sqLiteQuery.query(sqlDB, projection, selection,selectionArgs,null,null,sortOrder);
        return cursor;
    }


    public DBManager(Context context){
        DatabaseHelperUser databaseHelperUser = new DatabaseHelperUser(context);
        sqlDB = databaseHelperUser.getWritableDatabase();

    }
}
