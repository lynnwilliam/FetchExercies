package com.fetch.lynnwilliam

import com.fetch.lynnwilliam.webapi.FetchMockBadAPICall
import com.fetch.lynnwilliam.webapi.FetchMockOKAPICall
import com.fetch.lynnwilliam.webapi.RecordsRepository
import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.fail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

class ListScreenViewModelTests {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: RecordsRepository
    private lateinit var viewModel: ListScreenViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testViewModelDataLoading() = runTest {

        repository = FetchMockOKAPICall()
        viewModel = ListScreenViewModel(repository)
        viewModel.fetchRecords()
        advanceUntilIdle()
        val state = viewModel.records.value
        if (state is FetchState.DataFetched) {
            assertTrue("Data should not be empty", state.list.isNotEmpty())
        } else {
            fail("Expected DataFetched state was not reached")
        }
    }

    @Test
    fun testShouldFailGraceFullyWhenUsingRealAPICallWithError() = runTest {

        viewModel = ListScreenViewModel(FetchMockBadAPICall())
        viewModel.fetchRecords()
        advanceUntilIdle()
        val state = viewModel.records.value
        assertTrue("state should be FetchState.Error but is ${state.javaClass.canonicalName} ", state is FetchState.Error)
    }
}
