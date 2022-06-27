package com.parita.jetpackcomposeapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.hilt.navigation.compose.hiltViewModel
import com.parita.jetpackcomposeapp.ui.HomeScreen
import com.parita.jetpackcomposeapp.ui.theme.MeditationUITheme
import com.parita.jetpackcomposeapp.util.JetpackConstant
import com.parita.jetpackcomposeapp.util.Resource
import com.parita.jetpackcomposeapp.viewmodel.JetpackViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeditationUITheme {
                HomeScreen()
                // CallApi()
            }
        }
    }
}
@Composable
fun checkFeatureDetails(title: String) {
    when (title) {
        JetpackConstant.SleepMeditation -> {
            Log.d("TAG", "Sleep meditation")
            val context = LocalContext.current
            context.startActivity(Intent(context, SleepMeditation::class.java))
        }
        JetpackConstant.TipsForSleeping -> {}
        JetpackConstant.NightIsland -> {}
        JetpackConstant.CalmingSound -> {}
    }
}

/*
@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalMaterialApi
@Composable
fun CallApi(viewModel: JetpackViewModel= hiltViewModel()){
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val getAllUserData = viewModel.hitList.observeAsState()
    scope.launch {
        val result = viewModel.getMusicData()
        if (result is Resource.Success) {
            Log.d("TAG", "Result : ${getAllUserData.value}")
        } else if (result is Resource.Error) {
            Log.d("TAG", "Error: ${result.message}")
        }
    }
}*/
