package com.yogyakarta.yogyakartaapp.vo

import com.yogyakarta.yogyakartaapp.fixtures.PermissionStatus

data class Permission constructor(val status : PermissionStatus){
    companion object{
        fun granted() : Permission{
            return Permission(PermissionStatus.GRANTED)
        }
        fun denied() : Permission{
            return Permission(PermissionStatus.DENY)
        }
        fun blocked() : Permission{
            return Permission(PermissionStatus.BLOCKED)
        }
    }
}