package com.applocum_mm_demo.data.responce

class HomeResponse : ArrayList<HomeResponseItem>()

data class HomeResponseItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String?=null,
    val title: String,
    val url: String
)