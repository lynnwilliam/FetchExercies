package com.fetch.lynnwilliam.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fetch.lynnwilliam.BuildConfig
import com.fetch.lynnwilliam.data.FetchRecordsUseCase
import com.fetch.lynnwilliam.ui.FetchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    private val fetchRecordsUseCase: FetchRecordsUseCase
) : ViewModel() {

    private val _records = MutableStateFlow<FetchState>(FetchState.LoadingData)
    val records: StateFlow<FetchState> = _records.asStateFlow()

    init {
        fetchRecords()
    }

    fun fetchRecords() {
        viewModelScope.launch {
            _records.value = FetchState.LoadingData
            _records.value = fetchRecordsUseCase(BuildConfig.FETCH_URL)
        }
    }
}

class ListScreenViewModelFactory(private val fetchRecordsUseCase: FetchRecordsUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListScreenViewModel(fetchRecordsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
