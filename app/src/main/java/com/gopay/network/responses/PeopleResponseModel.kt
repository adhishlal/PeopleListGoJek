package com.gopay.network.responses

data class PeopleResponseModel(
    val next: String?,
    val results: MutableList<Results>
)

data class Results(
    val name: String
)
