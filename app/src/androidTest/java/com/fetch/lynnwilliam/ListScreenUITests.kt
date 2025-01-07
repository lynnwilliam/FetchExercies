package com.fetch.lynnwilliam

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListScreenUITests {


    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @After
    fun tearDown() {

    }

    @Test
    fun mainScreenIsDisplayed() {
        with(composeTestRule){
            onNodeWithText("Database Entries").assertIsDisplayed()
        }
    }

    @Test
    fun errorScreenIsDisplayed() {

        //how do I set the MainActivity screen to use the FakeData Repo Viewmodel ?

        with(composeTestRule){
            onNodeWithText("Oops Something Went Wrong").assertIsDisplayed()
        }
    }

    @Test
    fun dataIsShownOnScreen() {
//        MainActivity.DI.viewModelProvider = {
//            ListScreenViewModel(FetchMockOKAPICall())
//        }
//
//        with(composeTestRule){
//            onNodeWithText("Apple").assertIsDisplayed()
//        }
    }

    @Test
    fun loadingUIShowing() {
//        MainActivity.DI.viewModelProvider = {
//            TestViewModel()
//        }
//        composeTestRule.onNodeWithTag("LoadingUI").assertIsDisplayed()
    }

}