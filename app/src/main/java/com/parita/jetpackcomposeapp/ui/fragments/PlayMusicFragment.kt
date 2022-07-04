package com.parita.jetpackcomposeapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.findNavController
import com.parita.jetpackcomposeapp.R
import com.parita.jetpackcomposeapp.data.Images
import com.parita.jetpackcomposeapp.data.Share
import com.parita.jetpackcomposeapp.data.Track
import com.parita.jetpackcomposeapp.data.TrackDetails
import com.parita.jetpackcomposeapp.ui.HomeScreen
import com.parita.jetpackcomposeapp.ui.PlayMusicScreen
import com.parita.jetpackcomposeapp.ui.theme.MeditationUITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayMusicFragment : Fragment() {

    private lateinit var key:String
    private lateinit var title:String
    private lateinit var subtitle:String
    private lateinit var share: Share
    private lateinit var images:Images
    private lateinit var trackDetails: TrackDetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            key = arguments!!.getString("key")!!
            title = arguments!!.getString("title")!!
            subtitle = arguments!!.getString("subtitle")!!
            share = arguments!!.getParcelable("share")!!
            images = arguments!!.getParcelable("image")!!
        }
        trackDetails = TrackDetails(key, title, subtitle, share, images)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                MeditationUITheme {
                    PlayMusicScreen(findNavController(), trackDetails)
                }
            }
        }
    }
}