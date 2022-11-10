package com.example.playstore.model

data class Game(
    var id:Int?,
    var name:String,
    var description:String,
    var price:Int,
    var main_img_name:String?,
    var main_img_path:String?,
    var limit_age:Int,
    var sub_img_name:String?,
    var sub_img_path:String?,
    var invisible:Int?
)
