package com.example.playstore.model

import java.sql.Date
import java.time.LocalDateTime

data class Account(
    var id:String,
    var password:String,
    var is_admin:Int,
    var gameMoney:Int?,
    var birthDate:Date?,
    var basket:MutableList<Int>? = mutableListOf(),
    var myGame:MutableList<Pair<Int, LocalDateTime?>>? = mutableListOf()
)

data class AccountCoreInfo (
    var id:String,
    var password:String,
    var is_admin:Int,
    var gameMoney:Int?,
    var birthDate: Date?
)
