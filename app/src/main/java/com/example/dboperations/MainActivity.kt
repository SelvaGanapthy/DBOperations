package com.example.dboperations

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.database.Cursor
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IntegerRes
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
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


    companion object {
        val requestcode_permisson = 1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        databaseHelper = DatabaseHelper(this@MainActivity)
        edtUserName = findViewById<View>(R.id.edtUserName) as EditText
        edtMark1 = findViewById<View>(R.id.edtTotMark1) as EditText
        edtMark2 = findViewById<View>(R.id.edtMark2) as EditText
        edtTot = findViewById<View>(R.id.edtTot) as EditText

        if (checkPermission()) {

        } else {
            requestPermission()
        }



        intentfilter.addAction("com.android.CUSTOM_INTENT")
        this.registerReceiver(broadCr, intentfilter)


//        var arr = intArrayOf(4, 5, 1, 2, 8)
//        var i = 0
//        while (i < arr.size && (i + 1) != arr.size) {
//
//            if (arr[i] < arr[i + 1]) ;
//            else {
//
//                var temp = arr[i]
//                arr[i] = arr[i + 1]
//                arr[i + 1] = temp

//            }

//        }


//        Toast.makeText(this@MainActivity, "" + arr.toString(), Toast.LENGTH_SHORT).show()


    }


    fun dbInsert(v: View): Unit {

//        var i: Intent = Intent(this@MainActivity, BroadCastReceiver::class.java)
//        i.putExtra("BroadCast", "BDCR")
//        i.action = "com.android.CUSTOM_INTENT"
//        sendBroadcast(i)


        val a = Integer.parseInt(edtMark1?.text.toString())
        val b = Integer.parseInt(edtMark2?.text.toString())

        if (databaseHelper?.dbInsert(edtUserName?.text.toString(), a, b, total)!!) {
            Toast.makeText(this@MainActivity, "Insert Success", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this@MainActivity, "Insert Failed ", Toast.LENGTH_SHORT).show()
        }
    }

    fun dbGetAllData(v: View) {
//        databaseHelper?.dbSelectAll(edtUserName?.text.toString())
        databaseHelper?.getAllData()
    }


    override fun onDestroy() {
        unregisterReceiver(broadCr)
        super.onDestroy()
    }

    fun dbDelete(v: View): Unit {
        val del: Int = databaseHelper?.dbDelete("2")!!
        if (del > 0)
            Toast.makeText(this@MainActivity, "Data Deleted", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this@MainActivity, "Data not Deleted", Toast.LENGTH_LONG).show()
    }


    fun dbUpdate(v: View): Unit {
        databaseHelper?.dbUpdate(13, "Selva")
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


    fun checkPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.PROCESS_OUTGOING_CALLS)
        return (result == PackageManager.PERMISSION_GRANTED)
    }


    fun requestPermission(): Unit {

        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf(android.Manifest.permission.PROCESS_OUTGOING_CALLS),
            requestcode_permisson
        )

    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {

            requestcode_permisson -> if (grantResults.size > 0) {
                val StoragePermisson = grantResults[0] == PackageManager.PERMISSION_GRANTED

                if (StoragePermisson) {
                    Toast.makeText(
                        this@MainActivity, "Permission Granted",
                        Toast.LENGTH_LONG
                    ).show()
//                    onSuccess()
                } else {
                    Toast.makeText(this@MainActivity, "Permission Denied", Toast.LENGTH_LONG).show()
//                    onSuccess()
                }

            }

        }
    }


}
