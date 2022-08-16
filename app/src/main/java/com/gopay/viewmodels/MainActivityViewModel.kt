package com.gopay.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gopay.dispatcher.CoroutineDispatcherProvider
import com.gopay.network.ApiService
import com.gopay.network.responses.PeopleResponseModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val apiService: ApiService,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : BaseViewModel() {

    val peopleListLiveData = MutableLiveData<PeopleResponseModel>()

    fun callPeopleApi(page: Int) {
        networkStatus.postValue("PROGRESS")
        viewModelScope.launch(coroutineDispatcherProvider.io + coroutineExceptionHandler) {
            val responseModel = apiService.fetchPeople(page)
            networkStatus.postValue("SUCCESS")
            peopleListLiveData.postValue(responseModel)
        }
    }
}