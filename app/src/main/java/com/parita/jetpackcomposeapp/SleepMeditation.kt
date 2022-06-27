package com.parita.jetpackcomposeapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.parita.jetpackcomposeapp.ui.HomeScreen
import com.parita.jetpackcomposeapp.ui.theme.MeditationUITheme
import com.parita.jetpackcomposeapp.util.Resource
import com.parita.jetpackcomposeapp.viewmodel.JetpackViewModel
import kotlinx.coroutines.launch

class SleepMeditation : AppCompatActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeditationUITheme {
                CallViewModelApi()
            }
        }
    }
}