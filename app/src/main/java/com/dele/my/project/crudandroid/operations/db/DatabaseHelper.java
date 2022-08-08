package com.dele.my.project.crudandroid.operations.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.dele.my.project.crudandroid.operations.utils.AppConstants;
import com.dele.my.project.crudandroid.operations.utils.Helper;
import com.dele.my.project.crudandroid.operations.utils.Queries;

import java.util.Map;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, AppConstants.DATABASE_NAME, null, AppConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create the customer table
        sqLiteDatabase.execSQL(Queries.CREATE_CUSTOMER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    protected SQLiteDatabase write() {
        return this.getWritableDatabase();
    }

    protected SQLiteDatabase read() {
        return this.getReadableDatabase();
    }

    protected int remove(String table, String whereClause, String[] whereArgs) {
        return this.write().delete(table, whereClause, whereArgs);
    }

    protected Long create(Map<String, String> map, String table) {
        ContentValues contentValues = Helper.createRecord(map);
        Log.d(AppConstants.APPLICATION_TAG, "Data Five");
        return this.write().insert(table, null, contentValues);
    }

    protected Integer update(Map<String, String> map, String table, String whereStatement, String[] updatableArg) {
        ContentValues contentValues = Helper.createRecord(map);
        return this.write().update(table, contentValues, whereStatement, updatableArg);
    }

}
