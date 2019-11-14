package com.yogyakarta.yogyakartaapp.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import com.yogyakarta.yogyakartaapp.vo.Permission
class PermissionHelper constructor(
    private val context: Context,
    private val permissionToListen: String
) :
    MutableLiveData<Permission>() {

    override fun onActive() {
        val isPermissionGranted = ActivityCompat.checkSelfPermission(context, permissionToListen) == PackageManager.PERMISSION_GRANTED
        if(isPermissionGranted){
            postValue(Permission.granted())
        }else{
            postValue(Permission.denied())
        }
    }

}