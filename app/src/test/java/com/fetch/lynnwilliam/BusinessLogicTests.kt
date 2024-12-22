package com.fetch.lynnwilliam

import com.fetch.lynnwilliam.businessrules.BusinessRules
import com.fetch.lynnwilliam.data.Record
import org.junit.Assert
import org.junit.Test

class BusinessLogicTests {

    fun getDummyData(): List<Record>{
        return listOf(
            Record(id=1, listId = 2, name = "Apple"),
            Record(id=2, listId = 1, name = ""),
            Record(id=3, listId = 1, name = "Car"),
            Record(id=4, listId = 2, name = "Dentist")
        )
    }

    @Test
    fun testThatEmptyNamesAreRemoved() {
        val validData = BusinessRules().applyBusinessRules(getDummyData())
        // Assert that the list does not contain the record with an empty name
        Assert.assertFalse("Record with empty name should be removed", validData.any { it.id == 2 })
        Assert.assertTrue("Should have 3 records by have ${validData.size}", validData.size == 3)
    }
    //@TODO add more tests for the Grouping and ordering etc
}