package com.fetch.lynnwilliam

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fetch.lynnwilliam.di.TestViewModel
import com.fetch.lynnwilliam.webapi.FetchMockBadAPICall
import com.fetch.lynnwilliam.webapi.FetchMockOKAPICall
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListScreenUITests {

    /*
        NOTES: THESE TESTS WILL NOT RUN ON A PHYSICAL DEVICE, SO USE AN EMULATOR
        I DID NOT HAVE TIME TO DIAGNOSE WHY IT WONT RUN ON MY PHONE, SO USE AN EMULATOR
        NORMALLY OBVIOUSLY IT WOULD WORK ON A PHONE TOO, BUT I DID NOT HAVE TIME TO DIAGNOSE
     */

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @After
    fun tearDown() {
        MainActivity.DI.viewModelProvider = null
    }

    @Test
    fun mainScreenIsDisplayed() {
        with(composeTestRule){
            onNodeWithText("Database Entries").assertIsDisplayed()
        }
    }

    @Test
    fun errorScreenIsDisplayed() {
        MainActivity.DI.viewModelProvider = {
            ListScreenViewModel(FetchMockBadAPICall())
        }

        with(composeTestRule){
            onNodeWithText("Oops Something Went Wrong").assertIsDisplayed()
        }
    }

    @Test
    fun dataIsShownOnScreen() {
        MainActivity.DI.viewModelProvider = {
            ListScreenViewModel(FetchMockOKAPICall())
        }

        with(composeTestRule){
            onNodeWithText("Apple").assertIsDisplayed()
        }
    }

    @Test
    fun loadingUIShowing() {
        MainActivity.DI.viewModelProvider = {
            TestViewModel()
        }
        composeTestRule.onNodeWithTag("LoadingUI").assertIsDisplayed()
    }

}