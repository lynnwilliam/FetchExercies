package com.fetch.lynnwilliam.businessrules

import com.fetch.lynnwilliam.data.Record

//Business Rules are Important to the Business
//Display all the items grouped by "listId"
//Sort the results first by "listId" then by "name" when displaying.
//Filter out any items where "name" is blank or null.
class BusinessRules(){

    // sorting is important the business
    fun applyBusinessRules(list: List<Record>) : List<Record>{
        return list.filter { !it.name.isNullOrEmpty() }.sortedWith(compareBy<Record> { it.listId }.thenBy { it.name })
    }
}