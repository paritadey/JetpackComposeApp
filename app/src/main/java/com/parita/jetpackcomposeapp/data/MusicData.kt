package com.parita.jetpackcomposeapp.data

data class Artist (
    var id: String,
    var adamid: String,
    var hits: ArrayList<Hit>,
)

data class Hit (
    var track: Track,
   // var snippet: String,
   // var artist: Artist,
)

data class Images (
    var background: String,
    var coverart: String,
    var coverarthq: String,
    var joecolor: String,
    var overflow: String,
)

class Track (
    var layout: String,
    var type: String,
    var key: String,
    var title: String,
    var subtitle: String,
    var images: Images,
    var artists: ArrayList<Artist>,
    var url: String,
)

data class Tracks (
    var hits: ArrayList<Hit>
)

