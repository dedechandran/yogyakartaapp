package com.yogyakarta.yogyakartaapp.ui.account

import android.Manifest
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yogyakarta.yogyakartaapp.utils.PermissionHelper
import com.yogyakarta.yogyakartaapp.vo.Permission

class AccountViewModel constructor(private val context: Context) : ViewModel() {

    private var permissionLiveData = MutableLiveData<Permission>()

    fun getPermission() : LiveData<Permission>{
        if(permissionLiveData.value != null){
            return permissionLiveData
        }
        permissionLiveData = PermissionHelper(context,Manifest.permission.ACCESS_FINE_LOCATION)
        return permissionLiveData
    }

    fun setPermission(permission: Permission){
        permissionLiveData.value = permission
    }
}