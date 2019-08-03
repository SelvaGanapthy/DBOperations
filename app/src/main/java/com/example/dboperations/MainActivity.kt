package com.example.dboperations

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.database.Cursor
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IntegerRes
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.utils.BroadCastReceiver
import com.example.utils.DatabaseHelper
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var databaseHelper: DatabaseHelper? = null
    var edtUserName: EditText? = null
    var edtMark1: EditText? = null
    var edtMark2: EditText? = null
    var edtTot: EditText? = null
    var total: Int = 0
    var broadCr = BroadCastReceiver()
    var intentfilter: IntentFilter = IntentFilter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        databaseHelper = DatabaseHelper(this@MainActivity)
        edtUserName = findViewById<View>(R.id.edtUserName) as EditText
        edtMark1 = findViewById<View>(R.id.edtTotMark1) as EditText
        edtMark2 = findViewById<View>(R.id.edtMark2) as EditText
        edtTot = findViewById<View>(R.id.edtTot) as EditText

        intentfilter.addAction("com.android.CUSTOM_INTENT")
        this.registerReceiver(broadCr, intentfilter)
    }


    fun dbInsert(v: View): Unit {
//        var flag: Boolean
//        var r = StringBuilder("Hello12121 World")
//        for (i in 0 until r.length) {
//            if ('A' >= r[i] <= 'Z')
//                r.setCharAt(i, '\u0000')
//        }


        var i: Intent = Intent(this@MainActivity, BroadCastReceiver::class.java)
        i.putExtra("BroadCast", "BDCR")
        i.action = "com.android.CUSTOM_INTENT"
        sendBroadcast(i)

//        total = 0
//        var a = Integer.parseInt(edtMark1?.text.toString())
//        var b = Integer.parseInt(edtMark2?.text.toString())
//        total = a + b
//        edtTot?.setText(total.toString())
//
//        if (databaseHelper?.dbInsert(edtUserName?.text.toString(), a, b, total)!!) {
//            Toast.makeText(this@MainActivity, "Insert Success", Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(this@MainActivity, "Insert Failed ", Toast.LENGTH_SHORT).show()
//        }
    }

    fun dbGetAllData(v: View) {
//        databaseHelper?.dbSelectAll(edtUserName?.text.toString())
        databaseHelper?.getAllData()
    }


    fun update(view: View): Unit {
        databaseHelper?.dbUpdate(13, "Selva")

    }


    override fun onDestroy() {
        unregisterReceiver(broadCr)
        super.onDestroy()
    }

//    fun dbDelete(v: View): Unit {
//        val del: Int = databaseHelper?.dbDelete("2")!!
//        if (del > 0)
//            Toast.makeText(this@MainActivity, "Data Deleted", Toast.LENGTH_LONG).show()
//        else
//            Toast.makeText(this@MainActivity, "Data not Deleted", Toast.LENGTH_LONG).show()
//    }


    fun dbUpdate(v: View): Unit {


    }

//    fun dbView(v: View): Unit {
//        var cur: Cursor = databaseHelper?.getAllTableData()!!
//        try {
//
//            if (cur.moveToFirst()) {
//
//                while (!cur.isAfterLast()) {
//
//                    Toast.makeText(this@MainActivity, "Table Name=> " + cur.getString(0), Toast.LENGTH_LONG).show()
//                    cur.moveToNext()
//                }
//
//            }
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }

}
