package com.fetch.lynnwilliam

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fetch.lynnwilliam.data.Record
import com.fetch.lynnwilliam.ui.BlinkingTextRow
import com.fetch.lynnwilliam.ui.PrimaryButton
import com.fetch.lynnwilliam.ui.RecordItem
import com.fetch.lynnwilliam.ui.TitleText
import com.fetch.lynnwilliam.webapi.FetchMockOKAPICall
import kotlinx.coroutines.launch

@Composable
fun ListScreen(viewModel: ListScreenViewModel){

    LaunchedEffect(true) {
        viewModel.fetchRecords()
    }

    Column( modifier = Modifier.fillMaxSize()){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            TitleText(text = LocalContext.current.getString(R.string.fetchtitle))
        }

        val viewState = viewModel.records.collectAsState().value
        when(viewState){
            is FetchState.DataFetched -> DisplayData(viewModel, viewState.list )
            is FetchState.Error -> ShowError(viewModel, viewState.error)
            FetchState.LoadingData -> ShowLoading()
        }
    }
}

//make the UI into 3 different states
sealed class FetchState {
    data class DataFetched(val list: List<Record>) : FetchState()
    object LoadingData : FetchState()
    data class Error(val error: String) : FetchState()
}

//can show error if you want, just showing that you can pass it in
@Composable
fun ShowError(viewModel: ListScreenViewModel, error: String){

    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize(),  // Ensures the Column fills the entire screen
        horizontalAlignment = Alignment.CenterHorizontally,  // Center children horizontally
        verticalArrangement = Arrangement.Center
    ){
        TitleText(text = LocalContext.current.getString(R.string.fetcherror))
        PrimaryButton(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            text = LocalContext.current.getString(R.string.fetcherrorbutton),
            onClick = {
                coroutineScope.launch {
                    viewModel.fetchRecords()
                }
            }
        )
    }
}

@Composable
fun ShowLoading(){

    Column(
        modifier = Modifier.fillMaxSize().testTag("LoadingUI"),  // Ensures the Column fills the entire screen
        horizontalAlignment = Alignment.CenterHorizontally,  // Center children horizontally
        verticalArrangement = Arrangement.Center){
        BlinkingTextRow(text = LocalContext.current.getString(R.string.fetchloading))
    }
}

@Composable
fun DisplayData(viewModel: ListScreenViewModel, data: List<Record>){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Text(text = LocalContext.current.getString(R.string.header_listid))
        Text(text = LocalContext.current.getString(R.string.header_name))
        Text(text = LocalContext.current.getString(R.string.header_id))
    }

    val coroutineScope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxSize()) {

        Column {
            LazyColumn(modifier = Modifier.weight(1f)) {
                itemsIndexed(data) { index, record ->
                    RecordItem(record = record, odd = (index % 2 == 0))
                }
            }
            Button(
                onClick = {
                    coroutineScope.launch {
                            viewModel.fetchRecords()
                            }
                      },
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp)
            ) {
                Text(LocalContext.current.getString(R.string.fetchsuccessbutton))
            }
        }
    }
}


/*
    Personally I love Previews, and use them all the time
 */

@Preview
@Composable
fun PreviewListScreen(){
    val fakeRepo = FetchMockOKAPICall()
    ListScreen(
        ListScreenViewModel(
            initialState = FetchState.DataFetched(fakeRepo.getDummyData()))
        )
}

@Preview
@Composable
fun PreviewErrorListScreen(){
    ListScreen(
        ListScreenViewModel(
            initialState = FetchState.Error("Network Error"))
    )
}

@Preview
@Composable
fun PreviewLoadingListScreen(){
    ListScreen(
        ListScreenViewModel(
            initialState = FetchState.LoadingData)
    )
}

