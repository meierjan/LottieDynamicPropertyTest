package com.example.lottietest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
import com.example.lottietest.ui.theme.LottieTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LottieTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AnimationView(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun AnimationView(
    name: String,
    modifier: Modifier = Modifier,
) {
//    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.heart))
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.bike ))
    val progress by animateLottieCompositionAsState(composition)

    val dynamicProperties =
        rememberLottieDynamicProperties(
            rememberLottieDynamicProperty(
                property = LottieProperty.COLOR,
                value = Color.Green.toArgb(),
                keyPath =
                    arrayOf(
                        "**",
                        "primary",
                        "**",
                    ),
            ),
        )

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier.size(200.dp),
            progress = { progress },
            dynamicProperties = dynamicProperties,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LottieTestTheme {
        AnimationView("Android")
    }
}
