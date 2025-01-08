package com.example.gfgdatabaseapp2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(private val context: Context, factory: CursorFactory?): SQLiteOpenHelper(context,DATABASE_NAME, factory, DATABASE_VERSION) {
    companion object{
        const val DATABASE_NAME = "user_data"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "user"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_AGE = "age"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_NAME TEXT, $COLUMN_AGE INTEGER)")
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    fun insert(name:String, age: Int){
        val db = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_AGE,age)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }
    fun getName(): Cursor? {
        val db = readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }
}