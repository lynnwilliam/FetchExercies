package com.fetch.lynnwilliam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fetch.lynnwilliam.di.ViewModelDI
import com.fetch.lynnwilliam.ui.theme.FetchExercisesTheme

class MainActivity : ComponentActivity(), ViewModelDI {

    private lateinit var listScreenViewModel: ListScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FetchExercisesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    listScreenViewModel = DI.viewModelProvider?.invoke() ?: viewModel()
                    ListScreen(listScreenViewModel)
                }
            }
        }
    }

    override fun getViewModel(): ListScreenViewModel {
        if (::listScreenViewModel.isInitialized) {
            return listScreenViewModel
        } else {
            throw IllegalStateException("ViewModel not initialized")
        }
    }

    object DI {
        var viewModelProvider: (() -> ListScreenViewModel)? = null
    }
}
