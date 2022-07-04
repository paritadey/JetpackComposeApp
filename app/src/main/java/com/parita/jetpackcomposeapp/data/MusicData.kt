package com.parita.jetpackcomposeapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Artist(
    var id: String,
    var adamid: String,
    var hits: ArrayList<Hit>,
) : Parcelable

@Parcelize
data class Hit(
    var track: Track,
    // var snippet: String,
    // var artist: Artist,
) : Parcelable

@Parcelize
data class Images(
    var background: String,
    var coverart: String,
  /*  var coverarthq: String,
    var joecolor: String,
    var overflow: String,*/
) : Parcelable


@Parcelize
data class Track(
    var layout: String,
    var type: String,
    var key: String,
    var title: String,
    var subtitle: String,
    var share: Share,
    var images: Images,
    var artists: ArrayList<Artist>,
    var url: String,
) : Parcelable

data class TrackDetails(
    var key: String,
    var title: String,
    var subtitle: String,
    var share: Share,
    var images: Images
)

@Parcelize
data class Share(
    var subject: String,
    var href: String,
    var image: String,
    var html: String,
  //  var avatar: String
) : Parcelable
