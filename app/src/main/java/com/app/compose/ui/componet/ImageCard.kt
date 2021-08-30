package com.app.compose.ui.componet

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.compose.ui.exceptFirstWord
import com.app.compose.ui.firstWord


@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier
) {
    val context = LocalContext.current
    val borderColor = remember { mutableStateOf(borderColorConfig(clicked = false)) }
    val clicked = remember { mutableStateOf(false) }
    val clickAction: (Boolean) -> Unit = {
        borderColor.value = borderColorConfig(clicked = it)
    }
    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = 4.dp,
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .border(1.dp, borderColor.value, RoundedCornerShape(4.dp))
        ) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Fit,
                modifier = Modifier.clickable(
                    enabled = true,
                    onClickLabel = "Clickable image",
                    onClick = {
                        clicked.value = clicked.value.not()
                        clickAction(clicked.value)
                    }
                )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(
                        Brush.verticalGradient(
                            colors = (listOf(Color.Transparent, Color.Black)),
                            startY = 0f, endY = 120f
                        )
                    ),
                contentAlignment = Alignment.BottomStart
            ) {
                Column(modifier = Modifier.align(Alignment.Center)) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = title,
                        color = Color.White,
                        fontSize = 18.sp, fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontFamily = montserratFontFamily
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.Green)) {
                                append(contentDescription.firstWord())
                            }
                            append(" ${contentDescription.exceptFirstWord()}")
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
                        fontSize = 12.sp,
                        color = Color.White,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = montserratFontFamily
                    )
                }
            }
        }

    }
}

private fun borderColorConfig(clicked: Boolean): Color {
    return when {
        clicked -> Color.Red
        else -> Color.Transparent
    }
}
