package com.fetch.lynnwilliam.webapi

import com.fetch.lynnwilliam.businessrules.BusinessRules
import com.fetch.lynnwilliam.data.Record
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

// using an interface so we can mock out where the data comes from later
interface RecordsRepository {
    suspend fun fetchRecords(url: String): Response<List<Record>>
}

sealed class Response<out T> {
    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val exception: Exception) : Response<Nothing>()
}

class FetchAPICall: RecordsRepository {

    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun fetchRecords(url: String): Response<List<Record>>  = withContext(
        Dispatchers.IO) {

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        return@withContext try {
            client.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    val responseBody = response.body?.string() ?: throw IOException("Empty response body")
                    val dataList = json.decodeFromString<List<Record>>(responseBody)
                    Response.Success(BusinessRules().applyBusinessRules(dataList))
                } else {
                    Response.Error(Exception("response was not a success"))
                }
            }
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}

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
