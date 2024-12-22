package com.fetch.lynnwilliam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fetch.lynnwilliam.webapi.FetchAPICall
import com.fetch.lynnwilliam.webapi.RecordsRepository
import com.fetch.lynnwilliam.webapi.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class ListScreenViewModel(
    private val recordsRepo: RecordsRepository = FetchAPICall(),
    val initialState: FetchState = FetchState.LoadingData
): ViewModel() {

    //MutableStateFlow for thread safe network operations,
    val _records = MutableStateFlow<FetchState>(initialState)
    val records: StateFlow<FetchState> = _records.asStateFlow()

    open fun fetchRecords(){
        viewModelScope.launch {
            val fetchResponse = recordsRepo.fetchRecords(BuildConfig.FETCH_URL)
           if ( fetchResponse is Response.Success){
               _records.value = FetchState.DataFetched(fetchResponse.data)
           } else if ( fetchResponse is Response.Error){
               _records.value = FetchState.Error(fetchResponse.exception.toString())
           }
        }
    }
}