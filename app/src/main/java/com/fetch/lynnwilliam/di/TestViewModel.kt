package com.fetch.lynnwilliam.di

import androidx.lifecycle.viewModelScope
import com.fetch.lynnwilliam.FetchState
import com.fetch.lynnwilliam.ListScreenViewModel
import kotlinx.coroutines.launch

class TestViewModel: ListScreenViewModel() {

    override fun fetchRecords(){
        viewModelScope.launch {
            _records.value = FetchState.LoadingData
        }
    }
}