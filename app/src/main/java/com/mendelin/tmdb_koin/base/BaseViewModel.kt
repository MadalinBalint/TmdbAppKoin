package com.mendelin.tmdb_koin.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val error = MutableLiveData<String>()
    val isLoading = MutableLiveData(false)

    fun getErrorFilter(): LiveData<String> = error
    fun getLoadingObservable(): LiveData<Boolean> = isLoading

    fun onErrorHandled() {
        error.value = ""
    }

    var firstLoad: Boolean = true
}