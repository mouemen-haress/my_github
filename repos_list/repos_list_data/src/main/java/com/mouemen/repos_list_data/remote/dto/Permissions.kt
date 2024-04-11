package com.mouemen.repos_list_data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Permissions(
    @field:Json(name = "admin")
    val admin: Boolean,
    @field:Json(name = "maintain")
    val maintain: Boolean,
    @field:Json(name = "pull")
    val pull: Boolean,
    @field:Json(name = "push")
    val push: Boolean,
    @field:Json(name = "triage")
    val triage: Boolean
)