package com.example.utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.lang.StringBuilder

class DatabaseHelper(var context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_NAME: String = "STUDENT_DB"
        private val TABLE_NAME: String = "STUDENT_TABLE"
        private val UID: String = "id"
        private val NAME: String = "Name"
        private val MARK1: String = "Mark1"
        private val MARK2: String = "Mark2"
        private val TOTAL: String = "Total"
        private val DATABASE_VERSION: Int = 1
    }


    override fun onCreate(p0: SQLiteDatabase?) {
        try {
            p0?.execSQL(
                "CREATE TABLE $TABLE_NAME( $UID INTEGER PRIMARY KEY AUTOINCREMENT ,$NAME VARCHAR(255)," +
                        "$MARK1 VARCHAR(25),$MARK2 VARCHAR(25),$TOTAL VARCHAR(25) )"
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        try {
            p0?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(p0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun dbInsert(name: String, m1: Int, m2: Int, tot: Int): Boolean {

        var db: SQLiteDatabase = this.writableDatabase
        var cv: ContentValues = ContentValues()
        cv.put(NAME, name)
        cv.put(MARK1, m1)
        cv.put(MARK2, m2)
        cv.put(TOTAL, tot)
        var id: Long = db.insert(TABLE_NAME, null, cv)


        return if (id > -1) true else false
    }


    fun getAllData(): Unit {

        val db: SQLiteDatabase = this.writableDatabase
        var columns = arrayOf<String>(DatabaseHelper.UID, DatabaseHelper.NAME, MARK1, MARK2, TOTAL)
        var cursor: Cursor = db.query(TABLE_NAME, columns, null, null, null, null, null)
        cursor.moveToFirst()
        if (cursor.count != 0 && cursor.moveToFirst()) {

            while (cursor.moveToNext()) {
                val id: Int = cursor.getInt(cursor.getColumnIndex(UID))
                val name = cursor.getString(cursor.getColumnIndex(NAME))
                var m1 = cursor.getString(cursor.getColumnIndex(MARK1))
                var m2 = cursor.getString(cursor.getColumnIndex(MARK2))
                var tot: String = cursor.getString(cursor.getColumnIndex(TOTAL))

                Toast.makeText(context, " " + id + "  " + name + "  " + m1 + " " + m2 + " " + tot, Toast.LENGTH_SHORT)
                    .show()
            }

        } else {
            Toast.makeText(context, "No data Found", Toast.LENGTH_SHORT).show()
        }

        db.close()
    }


//    fun dbSelectAll(na: String): Unit {
//        val db: SQLiteDatabase = this.writableDatabase
//        var columns = arrayOf<String>(DatabaseHelper.UID, DatabaseHelper.NAME)
//        var cursor: Cursor =
//            db.query(TABLE_NAME, columns, DatabaseHelper.NAME + "='" + na + "'", null, null, null, null, null)
//
//        if (cursor.count != 0) {
//            while (cursor.moveToNext()) {
////                val index = cursor.getColumnIndex(UID)
//                val id = cursor.getInt(cursor.getColumnIndex(UID))
//                val Name = cursor.getString(cursor.getColumnIndex(NAME))
//                Toast.makeText(
//                    context, " ID : " + id + " Name :  " + Name,
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        } else {
//            Toast.makeText(context, " No data Found", Toast.LENGTH_SHORT).show()
//
//        }
//
//    }


    fun dbUpdate(id: Int, name: String): Unit {
        var db: SQLiteDatabase = this.writableDatabase
        var cv: ContentValues = ContentValues()
        cv.put(NAME, name)


        var i = db.update(TABLE_NAME, cv, UID + " = " + id, null)

        if (i > 0) {
            Toast.makeText(context, "Succ", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show()
        }


    }

    fun dbDelete(id: String): Int {
        val db: SQLiteDatabase = this.writableDatabase
        return db.delete(TABLE_NAME, "ID = ?", arrayOf<String>(id))
    }


}