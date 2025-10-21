package com.library.management.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.library.management.R
import com.library.management.utils.NetworkUtils
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current
    var hasInternet by remember { mutableStateOf<Boolean?>(null) }
    var retryTrigger by remember { mutableStateOf(0) }

    // 🔄 Check internet connection when splash loads or retried
    LaunchedEffect(retryTrigger) {
        hasInternet = NetworkUtils.isInternetAvailable(context)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        when (hasInternet) {
            null -> SplashLoading()
            true -> SplashWithAnimation {
                navController.navigate("dashboard") {
                    popUpTo("splash") { inclusive = true }
                }
            }
            false -> NoInternetView(onRetry = { retryTrigger++ })
        }
    }
}

@Composable
fun SplashLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Checking connection...",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun SplashWithAnimation(onFinish: suspend () -> Unit) {
    val scale = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        // Smooth scale animation
        scale.animateTo(1f, animationSpec = tween(800))
        delay(1500)
        onFinish()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // ✅ Use safe icon resource (vector or PNG)
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(120.dp)
                    .scale(scale.value)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "📚 Library Management",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp
                ),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Smart. Simple. Online.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}

@Composable
fun NoInternetView(onRetry: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "⚠️ No Internet Connection",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onRetry) {
                Text("Retry")
            }
        }
    }
}
