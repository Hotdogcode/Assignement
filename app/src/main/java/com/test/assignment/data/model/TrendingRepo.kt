package com.test.assignment.data.model

import com.squareup.moshi.Json


data class TrendingRepo(
    @Json(name="name")
    val name: Name,
    @Json(name="flags")
    val flags: Flags
)
data class Name(
    @Json(name="common")
    val common:String
)
data class Flags(
    @Json(name = "png")
    val flag:String
)