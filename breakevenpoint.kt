package com.example.myproject

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat

@Composable
fun BreakEvenPoint() {
    var fixcost by remember { mutableStateOf("") }
    var sellprice by remember { mutableStateOf("") }
    var varcost by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }


    var answer:String? by remember { mutableStateOf(null) }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(30.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Break Even Point",
            fontSize = 30.sp,
            lineHeight = 50.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 28.dp)
        )

        EditNumberField(
            label = R.string.fix,
            leadingIcon = R.drawable.dollar,
            value = fixcost,
            onValueChange = { fixcost = it },
            showError = showError
        )

        EditNumberField(
            label = R.string.priceunit,
            leadingIcon = R.drawable.dollar,
            value = sellprice,
            onValueChange = { sellprice = it },
            showError = showError
        )
        EditNumberField(
            label = R.string.varcost,
            leadingIcon = R.drawable.dollar,
            value = varcost,
            onValueChange = { varcost = it },
            showError = showError
        )
        Button(
            onClick = {
                if (validateInput(fixcost,  sellprice,varcost)) {
                    val fixcost = fixcost.toDouble()
                    val sellprice = sellprice.toDouble()
                    val varcost=varcost.toDouble()
                    answer = calculate_Break(fixcost,  sellprice,varcost)
                    showError = false
                } else {
                    showError = true
                }
            },
            modifier = Modifier.padding( bottom =32.dp,top=32.dp)
        ) {
            Text("Calculate")
        }
        if (showError) {
            Text(
                text = "Invalid input",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = androidx.compose.ui.graphics.Color.Red
                ),
                modifier = Modifier.padding(top = 4.dp)
            )
        } else {
            if (answer!=null) {
                Text(
                    text = stringResource(R.string.answer, answer!!),
                    style = MaterialTheme.typography.displaySmall
                )
            }
        }
    }
}
fun calculate_Break(fixcost: Double,  sellprice: Double = 15.0,varcost:Double): String {
    val answer =fixcost/(sellprice-varcost)
    return answer.toString()
}

