package com.parita.jetpackcomposeapp.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.parita.jetpackcomposeapp.R
import com.parita.jetpackcomposeapp.data.BottomMenuContent
import com.parita.jetpackcomposeapp.data.Feature
import com.parita.jetpackcomposeapp.itemDecoration.BottomMenuItem
import com.parita.jetpackcomposeapp.itemDecoration.FeatureItem
import com.parita.jetpackcomposeapp.ui.theme.*
import com.parita.jetpackcomposeapp.util.JetpackConstant
import com.parita.jetpackcomposeapp.util.JetpackConstant.CalmingSound
import com.parita.jetpackcomposeapp.util.JetpackConstant.NightIsland
import com.parita.jetpackcomposeapp.util.JetpackConstant.SleepMeditation
import com.parita.jetpackcomposeapp.util.JetpackConstant.TipsForSleeping
import java.util.*

@Composable
fun HomeScreen(findNavController: NavController) {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        Column {
            SectionGreetings(findNavController)
            SectionChips(
                chips = listOf(
                    stringResource(id = R.string.sweet_sleep),
                    stringResource(id = R.string.insomnia),
                    stringResource(id = R.string.depression)
                )
            )
            SectionDailyThoughts(findNavController)
            SectionFeature(findNavController)
        }
        SectionBottomMenu(
            items = listOf(
                BottomMenuContent(stringResource(id = R.string.home_menu), R.drawable.ic_home),
                BottomMenuContent(
                    stringResource(id = R.string.meditate_menu),
                    R.drawable.ic_bubble
                ),
                BottomMenuContent(stringResource(id = R.string.sleep_menu), R.drawable.ic_moon),
                BottomMenuContent(stringResource(id = R.string.music_menu), R.drawable.ic_music),
                BottomMenuContent(
                    stringResource(id = R.string.profile_menu),
                    R.drawable.ic_profile
                ),
            ), modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

fun sectionData(): List<Feature> {
    return listOf(
        Feature(
            title = SleepMeditation,
            R.drawable.ic_headphone,
            BlueViolet1,
            BlueViolet2,
            BlueViolet3
        ),
        Feature(
            title = TipsForSleeping,
            R.drawable.ic_videocam,
            LightGreen1,
            LightGreen2,
            LightGreen3
        ),
        Feature(
            title = NightIsland,
            R.drawable.ic_headphone,
            OrangeYellow1,
            OrangeYellow2,
            OrangeYellow3
        ),
        Feature(title = CalmingSound, R.drawable.ic_headphone, Beige1, Beige2, Beige3)
    )
}

fun getCurrentTime(): String {
    val timeOfDay: Int = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return if (timeOfDay >= 0 && timeOfDay < 12) JetpackConstant.GM
    else if (timeOfDay >= 12 && timeOfDay < 16) JetpackConstant.GA
    else if (timeOfDay >= 16 && timeOfDay < 21) JetpackConstant.GE
    else if (timeOfDay >= 21 && timeOfDay < 24) JetpackConstant.GN
    else JetpackConstant.NA
}

@Composable
fun SectionGreetings(findNavController: NavController) {
    val time = getCurrentTime()
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "$time, Parita", style = MaterialTheme.typography.h2)
            Text(
                text = stringResource(id = R.string.greetings),
                style = MaterialTheme.typography.body1
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = stringResource(id = R.string.search_image),
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    findNavController.navigate(R.id.openSearchFragment)
                }
        )
    }
}

@Composable
fun SectionChips(
    chips: List<String>
) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    LazyRow {
        items(chips.size) {
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) ButtonBlue
                        else DarkerButtonBlue
                    )
                    .padding(15.dp)) {
                Text(text = chips[it], color = TextWhite)
            }
        }
    }
}

@Composable
fun SectionDailyThoughts(findNavController: NavController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(
                RoundedCornerShape(10.dp)
            )//.clickable {  }
            .background(LightRed)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.clickable { findNavController.navigate(R.id.HomeToNotes) }) {
            Text(
                text = stringResource(id = R.string.daily_thought),
                style = MaterialTheme.typography.h2
            )
            Text(
                text = stringResource(id = R.string.meditation_3_mins),
                style = MaterialTheme.typography.body1,
                color = TextWhite
            )
        }
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = "Play",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SectionFeature(findNavController: NavController) {
    Column {
        Text(
            text = stringResource(id = R.string.featured),
            style = MaterialTheme.typography.h2,
            modifier = Modifier.padding(15.dp)
        )
    }
    var feature = sectionData()
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
        modifier = Modifier.fillMaxHeight()
    ) {
        items(feature.size) {
            FeatureItem(feature[it], findNavController)
        }
    }
}

@Composable
fun SectionBottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically, modifier = modifier
            .fillMaxWidth()
            .background(
                DeepBlue
            )
            .padding(15.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index
            }
        }
    }
}
