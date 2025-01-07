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

