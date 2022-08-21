package com.dimits.smsreader

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getSMS()
    }

    private fun getSMS(){
        val uri = Uri.parse("content://sms/inbox")
        val projection = arrayOf(SMS_SENDER, SMS_BODY)
        var cursor : Cursor = contentResolver.query(uri,projection,null,null,null)!!
        while (cursor.moveToNext()){
            for (i in 0 until cursor.columnCount){
                Log.i(LOG_TAG,"${i} - ${ cursor.getColumnName(i)} - ${cursor.getString(i)}")
            }
        }

    }


    companion object{
        private const val LOG_TAG = "MAIN ACTIVITY"
        private const val SMS_BODY = "body"
        private const val SMS_SENDER = "address"
    }
}