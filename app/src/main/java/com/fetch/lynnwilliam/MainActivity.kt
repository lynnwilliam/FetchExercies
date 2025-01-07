package com.fetch.lynnwilliam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.fetch.lynnwilliam.data.FetchRecordsUseCase
import com.fetch.lynnwilliam.ui.ListScreen
import com.fetch.lynnwilliam.ui.theme.FetchExercisesTheme
import com.fetch.lynnwilliam.webapi.FetchAPICall

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fetchRecordsUseCase = FetchRecordsUseCase(FetchAPICall())
        val viewModelFactory = ListScreenViewModelFactory(fetchRecordsUseCase)

        setContent {
            FetchExercisesTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val listScreenViewModel: ListScreenViewModel by viewModels { viewModelFactory }
                    ListScreen(listScreenViewModel)
                }
            }
        }
    }
}
