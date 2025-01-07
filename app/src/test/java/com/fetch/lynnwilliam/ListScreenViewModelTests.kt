package com.fetch.lynnwilliam

import com.fetch.lynnwilliam.data.FetchRecordsUseCase
import com.fetch.lynnwilliam.mocks.FetchMockBadAPICall
import com.fetch.lynnwilliam.mocks.FetchMockOKAPICall
import com.fetch.lynnwilliam.ui.FetchState
import com.fetch.lynnwilliam.viewmodels.ListScreenViewModel
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test

class ListScreenViewModelTests {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

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
        val viewModel = ListScreenViewModel(fetchRecordsUseCase = FetchRecordsUseCase(FetchMockOKAPICall()))
        viewModel.fetchRecords()
        advanceUntilIdle()
        assertTrue("Data should not be empty", (viewModel.records.value as? FetchState.DataFetched)?.list?.isNotEmpty() ?: false)
    }

    @Test
    fun testShouldFailGracefullyWhenUsingRealAPICallWithError() = runTest {
        val viewModel = ListScreenViewModel(fetchRecordsUseCase = FetchRecordsUseCase(FetchMockBadAPICall()))
        viewModel.fetchRecords()
        advanceUntilIdle()
        assertTrue("State should be FetchState.Error", viewModel.records.value is FetchState.Error)
    }

}
