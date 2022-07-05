package com.parita.jetpackcomposeapp.itemDecoration

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.parita.jetpackcomposeapp.R
import com.parita.jetpackcomposeapp.data.Feature
import com.parita.jetpackcomposeapp.ui.theme.ButtonBlue
import com.parita.jetpackcomposeapp.ui.theme.TextWhite
import com.parita.jetpackcomposeapp.util.JetpackConstant.SleepMeditation
import com.parita.jetpackcomposeapp.util.standardQuadFromTo

@Composable
fun FeatureItem(feature: Feature, findNavController: NavController) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(feature.darkColor)
    ) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        //Medium color path
        val mediumPath = mediumColorPath(width, height)
        //Light color path
        val lightPath = lightColorPath(width, height, "featureItem")

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(path = mediumPath, color = feature.mediumColor)
            drawPath(path = lightPath, color = feature.lightColor)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = feature.title,
                style = MaterialTheme.typography.h2,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart)
            )
            Icon(
                painter = painterResource(id = feature.iconId),
                contentDescription = feature.title,
                tint = Color.White,
                modifier = Modifier.align(Alignment.BottomStart)
            )
            Text(
                text = "Start",
                color = TextWhite,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        checkCardDetails(findNavController, feature.title)
                    }
                    .align(
                        Alignment.BottomEnd
                    )
                    .clip(RoundedCornerShape(10.dp))
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp)
            )
        }
    }
}

fun lightColorPath(width: Int, height: Int, classname: String): Path {
    val lightColoredPoint1 = Offset(0f, height * 0.35f)
    val lightColoredPoint2 = Offset(width * 0.1f, height * 0.4f)
    val lightColoredPoint3 = Offset(width * 0.3f, height * 0.35f)
    val lightColoredPoint4 = Offset(width * 0.65f, height.toFloat())
    val lightColoredPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

    val lightColoredPath = Path().apply {
        moveTo(lightColoredPoint1.x, lightColoredPoint1.y)
        standardQuadFromTo(lightColoredPoint1, lightColoredPoint2)
        standardQuadFromTo(lightColoredPoint2, lightColoredPoint3)
        standardQuadFromTo(lightColoredPoint3, lightColoredPoint4)
        standardQuadFromTo(lightColoredPoint4, lightColoredPoint5)
        if (classname.equals("playMusic")) {
            lineTo(width.toFloat() + 150f, height.toFloat() - 0f)
            lineTo(-0f, height.toFloat() + 100f)
        } else if (classname.equals("featureItem")) {
            lineTo(width.toFloat() + 100f, height.toFloat() - 100f)
            lineTo(-100f, height.toFloat() + 100f)
        }
        close()
    }
    return lightColoredPath
}

fun mediumColorPath(width: Int, height: Int): Path {
    val mediumColoredPoint1 = Offset(0f, height * 0.3f)
    val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
    val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
    val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
    val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

    val mediumColoredPath = Path().apply {
        moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
        standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
        standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
        standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
        standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
        lineTo(width.toFloat() + 100f, height.toFloat() - 100f)
        lineTo(-100f, height.toFloat() + 100f)
        close()
    }
    return mediumColoredPath
}

fun checkCardDetails(findNavController: NavController, title: String) {
    when (title) {
        SleepMeditation -> findNavController.navigate(R.id.viewSleepMeditation)
    }
}
