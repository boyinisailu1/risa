package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.w3c.dom.Text

@Composable
fun CalculatorButtonGrid(onButtonClick: (String) -> Unit) {
    val buttons = listOf(
        "AC", "%", "⌫", "÷",
        "7", "8", "9", "x",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "History", "0", ".", "="
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(buttons) { label ->
            CalculatorButton(
                label = label,
                onClick = { onButtonClick(label) }
            )
        }
    }
}

@Composable
fun CalculatorButton(label: String, onClick: () -> Unit) {
    val isOperator = label in listOf("AC", "%", "⌫", "÷", "x", "-", "+", "=", ".", "History")
    val bgColor = when {
        label == "=" -> Color(0xFFFF9522)  // Orange for equals
        isOperator -> Color(0xFF333333)    // Dark gray for ops
        else -> Color(0xFFDADADA)          // Light light yellow for numbers
    }
    val textColor = if (label == "=" || isOperator) Color.White else Color.Black

    // New rounded rectangle shape (pill/stadium)
    val buttonShape = RoundedCornerShape(percent = 50)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (label == "0") 80.dp else 72.dp)
            .clip(buttonShape)
            .background(bgColor)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick()
            }
            .padding(horizontal = 0.dp, vertical = 0.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = textColor,
            fontSize = if (label.length > 1) 22.sp else 36.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}

