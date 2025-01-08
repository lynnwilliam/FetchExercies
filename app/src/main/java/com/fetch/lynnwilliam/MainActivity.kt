package com.fetch.lynnwilliam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.fetch.lynnwilliam.ui.ListScreen
import com.fetch.lynnwilliam.ui.theme.FetchExercisesTheme
import com.fetch.lynnwilliam.viewmodels.ListScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listScreenViewModel: ListScreenViewModel by viewModels()

        setContent {
            FetchExercisesTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ListScreen(listScreenViewModel)
                }
            }
        }
    }
}
