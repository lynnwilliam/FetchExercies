package com.fetch.lynnwilliam.data

import kotlinx.serialization.Serializable

@Serializable
data class Record(val id: Int, val listId: Int, val name: String? = null)