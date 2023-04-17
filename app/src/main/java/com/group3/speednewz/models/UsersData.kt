package com.group3.speednewz.models

import java.io.Serializable

data class UsersData(
    val userId: Int,
    val username: String,
    val password: String,
) : Serializable
