package com.yogyakarta.yogyakartaapp.vo

import com.yogyakarta.yogyakartaapp.fixtures.StatusFixture

data class Resource<T>(val status : StatusFixture,val data : T?, val message : String?) {
    companion object{
        fun <T> success(data : T?) : Resource<T>{
            return Resource(StatusFixture.SUCCESS,data,null)
        }
        fun <T> loading() : Resource<T>{
            return Resource(StatusFixture.LOADING,null,null)
        }
        fun <T> error(message: String?) : Resource<T>{
            return Resource(StatusFixture.ERROR,null,message)
        }

    }
}