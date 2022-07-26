package com.dele.my.project.crudandroid.operations.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    protected Long create(Map<String, String> map, String table) {
        ContentValues contentValues = Helper.createRecord(map);
        return this.write().insert(table, null, contentValues);
    }

}
