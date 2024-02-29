package com.rashmi.springct.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Employee(
    @PrimaryKey
    val id: String,
    val name: String = "",
    val age: Int = 0,
    val address: String = "",
    val city: String = ""
)
