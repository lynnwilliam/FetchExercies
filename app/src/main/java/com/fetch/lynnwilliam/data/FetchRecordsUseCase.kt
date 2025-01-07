package com.fetch.lynnwilliam.data

import com.fetch.lynnwilliam.ui.FetchState
import com.fetch.lynnwilliam.webapi.RecordsRepository

class FetchRecordsUseCase(private val recordsRepository: RecordsRepository) {

    suspend operator fun invoke(url: String): FetchState {
        val fetchResult = recordsRepository.fetchRecords(url)
        return when(fetchResult){
            is com.fetch.lynnwilliam.webapi.Response.Error ->{
                FetchState.Error(fetchResult.exception.toString())
            }
            is com.fetch.lynnwilliam.webapi.Response.Success -> {
                FetchState.DataFetched( fetchResult.data)
            }
        }
    }
}