package com.fetch.lynnwilliam.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fetch.lynnwilliam.data.Record
import com.fetch.lynnwilliam.ui.theme.LocalCustomColors
import kotlinx.coroutines.delay

/*
        place components we will reuse all over the app
 */

@Composable
fun BlinkingTextRow(text: String) {
    var isVisible by remember { mutableStateOf(true) }

    // Toggle visibility periodically
    LaunchedEffect(Unit) {
        while (true) {
            isVisible = !isVisible

            var delay= 500L
            if(isVisible){
                delay=1000L
            }else{
                delay=450L
            }
            delay(delay) // Blinking interval in milliseconds
        }
    }

    Row {
        Text(text = " ")
        Spacer(modifier = Modifier.width(16.dp))
        // Blinking Text (conditionally rendered)
        if (isVisible) {
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colors.primary,
                text = text,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )
        }
    }
}



@Composable
fun RecordItem(record: Record, odd: Boolean){
    val customColors = LocalCustomColors.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (odd) customColors.oddRowColor else customColors.evenRowColor),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Cell(text = record.listId.toString(),odd)
        Cell(text = record.name ?: "",odd)
        Cell(text = record.id.toString(),odd)
    }
}

@Composable
fun Cell(text: String, odd: Boolean){
    val customColors = LocalCustomColors.current
    Text(
        textAlign = TextAlign.Center,
        text = text,
        color = if (odd) customColors.textColor else customColors.textColor,
        modifier = Modifier
            .padding(10.dp))
}

@Composable
fun PrimaryButton(modifier : Modifier = Modifier,text: String, onClick: () -> Unit){
    Button( modifier = modifier,
        onClick = { onClick() } ) {
        Text(text = text)
    }
}

@Composable
fun TitleText(text: String){
    Text(
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.primary,
        text = text,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    )
}