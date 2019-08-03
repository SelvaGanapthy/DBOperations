package com.example.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import kotlin.contracts.contract

class BroadCastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, p1: Intent?) {

        if (p1?.action.equals("com.android.CUSTOM_INTENT")) {
            Toast.makeText(context, "Custom BroadCast Receiver", Toast.LENGTH_SHORT).show()
        }

    }
}