package com.gopay.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException

open class BaseViewModel : ViewModel() {

    public val networkStatus = MutableLiveData<String>()

    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        handleException(throwable)
    }

    private fun handleException(throwable: Throwable) {

        if (throwable is HttpException) {
            networkStatus.postValue("ERROR")
        }
    }
}