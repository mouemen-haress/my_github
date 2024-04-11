package com.mouemen.mygithub

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun SplasScreen(
    onDelayFinished: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LaunchedEffect(key1 = true) {
            CoroutineScope(Dispatchers.Main).launch {
                delay(3000)
                onDelayFinished()
            }
        }
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null, // Content description is required for accessibility
            modifier = Modifier.size(200.dp) // Adjust size as needed
        )
    }
}