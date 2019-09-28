package com.dsm.dsmmarketandroid.presentation.util

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionUtil {
    private const val READ_EXTERNAL_STORAGE_CODE = 1000

    fun requestReadExternalStorage(activity: Activity) =
        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), READ_EXTERNAL_STORAGE_CODE)

    fun isReadExternalStorageAllow(activity: Activity): Boolean =
        ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
}