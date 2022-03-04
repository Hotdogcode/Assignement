package com.test.assignment.data.model

import com.squareup.moshi.Json


data class TrendingRepo(
    @Json(name="name")
    val name: Name,

    @Json(name="flags")
    val flags: Flags,

    @Json(name="cca2")
    val flag : String,

    var isSelected: Boolean = false
)
data class Name(
    @Json(name="common")
    val common:String,

    @Json(name="official")
    val official:String
)
data class Flags(
    @Json(name = "png")
    val flag:String
)