package com.example.playstore.model

import java.sql.Date

data class Account(
    var id:String,
    var password:String,
    var is_admin:Int,
    var birthDate:Date?
)
