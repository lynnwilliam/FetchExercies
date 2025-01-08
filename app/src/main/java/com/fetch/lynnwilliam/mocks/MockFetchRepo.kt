package com.fetch.lynnwilliam.mocks

import androidx.lifecycle.ViewModel
import com.fetch.lynnwilliam.businessrules.BusinessRules
import com.fetch.lynnwilliam.data.Record
import com.fetch.lynnwilliam.webapi.RecordsRepository
import com.fetch.lynnwilliam.webapi.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class FetchMockOKAPICall: RecordsRepository {

    fun getDummyData(): List<Record>{
        return listOf(
            Record(id=1, listId = 2, name = "Apple"),
            Record(id=2, listId = 1, name = "Ball"),
            Record(id=3, listId = 1, name = "Car"),
            Record(id=4, listId = 2, name = "Dentist")
        )
    }

    override suspend fun fetchRecords(url: String): Response<List<Record>> {
        return Response.Success(BusinessRules().applyBusinessRules(getDummyData()))
    }
}

class FetchMockBadAPICall: RecordsRepository {
    override suspend fun fetchRecords(url: String): Response<List<Record>> {
        return Response.Error(Exception("dummy error"))
    }
}

//@HiltViewModel
//class FakeMainViewModel @Inject constructor(
//    repository: FetchMockOKAPICall
//) : ViewModel() {
//
//}