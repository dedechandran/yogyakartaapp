package com.yogyakarta.yogyakartaapp.data.repository

interface RepositoryCallback<T> {
    fun onSuccess(data : T)
    fun onFailure(t : Throwable)
}