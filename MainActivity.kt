package com.example.myproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myproject.ui.theme.MyprojectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyprojectTheme {
                val navController=rememberNavController()
                NavHost(navController,startDestination="first"){
                    composable("first"){
                        imageCard(navController=navController)
                    }
                    composable("mortage"){
                        MortgageCalculator ()
                    }
                    composable("loan"){
                        LoanCalculator ()
                    }
                    composable("breakevenpoint"){
                        BreakEvenPoint ()
                    }
                    composable("paybackperiod"){
                        PaybackPeriod ()
                    }

                }
            }
        }
    }
}

@Composable
fun ClickableImage(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
            .clickable(onClick = onClick),
        contentScale = ContentScale.Crop
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun imageCard(navController: NavController,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .height(56.dp),     // Set the height of the TopAppBar
                contentAlignment = Alignment.Center
            ) {
            TopAppBar(
                title = { Text( text = "Financial Calculators",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 35.sp,
                        color = MaterialTheme.colorScheme.inversePrimary
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )
                }
            )
        }},
        content = {
            Column(
                modifier = Modifier.padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {

                    item {
                        Column(
                            modifier = Modifier.padding(start=5.dp,top=50.dp,bottom=5.dp,end=5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            ClickableImage(
                                painter = painterResource(R.drawable.mortage),
                                contentDescription = "mortgage",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .aspectRatio(1f)
                            ) {
                                navController.navigate("mortage")
                            }
                            LimitedSpaceText(
                                text = stringResource(R.string.mortage),
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(50.dp),
                                fontSize = 16.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    item {
                        Column(
                            modifier = Modifier.padding(start=5.dp,top=50.dp,bottom=5.dp,end=5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            ClickableImage(
                                painter = painterResource(R.drawable.loan),
                                contentDescription = "loan",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .aspectRatio(1f)
                            ) {
                                navController.navigate("loan")
                            }
                            LimitedSpaceText(
                                text = stringResource(R.string.loan),
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(50.dp),
                                fontSize = 16.sp,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    item {
                        Column(
                            modifier = Modifier.padding(5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            ClickableImage(
                                painter = painterResource(R.drawable.even),
                                contentDescription = "even",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .aspectRatio(1f)
                            ){
                                navController.navigate("breakevenpoint")
                            }
                            LimitedSpaceText(
                                text = stringResource(R.string.even),
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(50.dp),
                                fontSize = 16.sp,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    item {
                        Column(
                            modifier = Modifier.padding(5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            ClickableImage(
                                painter = painterResource(R.drawable.payback),
                                contentDescription = "payback",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .aspectRatio(1f)
                            ){
                                navController.navigate("paybackperiod")
                            }
                            LimitedSpaceText(
                                text = stringResource(R.string.payback),
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(50.dp),
                                fontSize = 16.sp,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    )
    }
@Composable
fun LimitedSpaceText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 14.sp,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        modifier = modifier
            .widthIn(max = 300.dp)
            .heightIn(max = 50.dp),
        fontSize = fontSize,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign
    )
}


