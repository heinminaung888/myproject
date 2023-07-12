package com.example.myproject

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.Month
import kotlin.math.pow

@Composable
fun MortgageCalculator() {

    var billAmount by remember { mutableStateOf("") }
    var downPayment by remember { mutableStateOf("") }
    var mortgageTime by remember { mutableStateOf("") }
    var interestRate by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

//    var monthly_pay by remember { mutableStateOf("0.0") }

//    lateinit var mortageResult:mortageData

    var mortageResult: mortageData? by remember { mutableStateOf(null) }

    var monthOfList: List<mortageData> by remember { mutableStateOf(listOf()) }


    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(30.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Mortgage Calculator",
            fontSize = 30.sp,
            lineHeight = 50.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 28.dp)
        )

        EditNumberField(
            label = R.string.bill_amount,
            leadingIcon = R.drawable.dollar,
            value = billAmount,
            onValueChange = { billAmount = it },
            showError = showError
        )

        EditNumberField(
            label = R.string.down_payment,
            leadingIcon = R.drawable.percentage,
            value = downPayment,
            onValueChange = { downPayment = it },
            showError = showError
        )

        EditNumberField(
            label = R.string.period,
            leadingIcon = R.drawable.time,
            value = mortgageTime,
            onValueChange = { mortgageTime = it },
            showError = showError
        )
        EditNumberField(
            label = R.string.interest,
            leadingIcon = R.drawable.percentage,
            value = interestRate,
            onValueChange = { interestRate = it },
            showError = showError,
        )

        Button(
            onClick = {
                if (validateInput(billAmount, downPayment, mortgageTime, interestRate)) {
                    val amount = billAmount.toDouble()
                    val downPay = downPayment.toDouble()
                    val interest = interestRate.toDouble()
                    val time=mortgageTime.toDouble()
                    mortageResult = calculatePayment(amount, time,downPay, interest)
                    showError = false
                } else {
                    showError = true
                }
            },
            modifier = Modifier.padding(bottom = 32.dp)
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
            if(mortageResult != null){
                Text(
                    text = stringResource(R.string.monthly_payment, mortageResult?.monthlyPay.toString()),
                    style = MaterialTheme.typography.displaySmall
                )
            }

        }
    }
}

// modified TextField Function

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNumberField(
    @StringRes label: Int,
    @DrawableRes leadingIcon: Int,
    value: String,
    onValueChange: (String) -> Unit,
    showError: Boolean
) {
    TextField(
        value = value,
        leadingIcon = { Icon(painter = painterResource(id = leadingIcon), contentDescription = null) },
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text(stringResource(label)) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        isError = showError,
        modifier = Modifier.padding(bottom=28.dp)
    )
}

// checking the input data valid or not
fun validateInput(vararg values: String): Boolean {
    for (value in values) {
        if (value.isEmpty() || value.toDoubleOrNull() == null) {
            return false
        }
    }
    return true
}
fun calculatePayment(amount: Double, downPay: Double, time:Double,interest: Double = 15.0): mortageData {
    val loan=amount-(amount*downPay)
    val totalMonth=time*12
    val monthlyInterestrate=interest/12
    val monthlyPay=loan*(monthlyInterestrate*(1+monthlyInterestrate).pow(totalMonth))/((1+monthlyInterestrate).pow(totalMonth-1))
    val interestAmount=loan*monthlyInterestrate
    val principal=loan-interestAmount
    val mortageda = mortageData(interestAmount, principal,totalMonth, monthlyPay)

    return mortageda
}


data class mortageData(var interestAmount: Double,var principal: Double,var totalMonth:Double, var monthlyPay:Double)

