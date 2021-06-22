package com.qiufeng.permissionx

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.qiufeng.mylibrary.PermissionX

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun callPhone(view: View) {
        requestPermissions()
    }

    private fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10010")
            startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    fun requestPermissions() {
        PermissionX.request(this, Manifest.permission.CALL_PHONE) { allGranted, deniedList ->
            if (allGranted) call()
            else Toast.makeText(this, "You denied $deniedList", Toast.LENGTH_SHORT).show()
        }
    }
}