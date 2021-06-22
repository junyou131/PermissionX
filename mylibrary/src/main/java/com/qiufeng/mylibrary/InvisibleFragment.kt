package com.qiufeng.mylibrary

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

/**
 * Author： QF
 * Time： 2021/6/22
 * Description：
 */
private const val TAG = "InvisibleFragment"

class InvisibleFragment : Fragment() {
    private var callback: PermissionCallback? = null

    fun requestNow(cb: PermissionCallback, vararg permissions: String) {
        callback = cb
        requestPermissions(permissions, 1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }
            val allGranted = deniedList.isEmpty()
            callback?.let { it(allGranted, deniedList) }
        }
    }
}

typealias PermissionCallback = (Boolean, List<String>) -> Unit