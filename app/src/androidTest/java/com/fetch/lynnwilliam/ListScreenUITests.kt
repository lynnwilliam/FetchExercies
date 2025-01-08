package com.fetch.lynnwilliam

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ListScreenUITests {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val composeTestRule = createComposeRule()
    
    @Before
    fun setUp() {
        hiltRule.inject()
    }

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

        with(composeTestRule){
            onNodeWithText("Oops Something Went Wrong").assertIsDisplayed()
        }
    }

}