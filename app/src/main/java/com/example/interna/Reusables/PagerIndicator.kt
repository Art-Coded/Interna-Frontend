package com.example.interna.Reusables

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.interna.ui.theme.gradient_start

@Composable
fun PagerIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        repeat(pageCount) { index ->
            val isSelected = index == currentPage

            val size by animateDpAsState(
                targetValue = if (isSelected) 10.8.dp else 8.dp,
                label = "DotSize"
            )

            val color by animateColorAsState(
                targetValue = if (isSelected)
                    gradient_start
                else
                    gradient_start.copy(alpha = 0.5f),
                label = "DotColor"
            )


            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(size)
                    .background(color = color, shape = CircleShape)
            )
        }
    }
}