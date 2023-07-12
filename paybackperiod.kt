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
fun PaybackPeriod() {
    var investment by remember { mutableStateOf("") }
    var cashflow by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }


    var answer:String? by remember { mutableStateOf(null) }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(30.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Payback Period ",
            fontSize = 30.sp,
            lineHeight = 50.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 28.dp)
        )

        EditNumberField(
            label = R.string.initial_investment,
            leadingIcon = R.drawable.dollar,
            value = investment,
            onValueChange = { investment = it },
            showError = showError
        )

        EditNumberField(
            label = R.string.cash_flow,
            leadingIcon = R.drawable.dollar,
            value = cashflow,
            onValueChange = { cashflow = it },
            showError = showError
        )
        Button(
            onClick = {
                if (validateInput(investment,  cashflow)) {
                    val investment = investment.toDouble()
                    val cashflow = cashflow.toDouble()
                    answer = calculate_Paybackperiod(investment,  cashflow)
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
                    text = stringResource(R.string.payback_period, answer!!),
                    style = MaterialTheme.typography.displaySmall
                )
            }
        }
    }
}
fun calculate_Paybackperiod(investment: Double,  cashflow: Double = 15.0): String {
    val answer = investment / cashflow
    return answer.toString()
}

