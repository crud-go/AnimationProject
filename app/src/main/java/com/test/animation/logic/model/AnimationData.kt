package com.test.animation.logic.model

data class AnimationProfile (var img_url:String,var name:String,var detail: String)
data class AnimationWeek(var img_url:String,var name:String,var type:String,var detail:String,var stu:String)
data class SearchResult(var name: String,var img_url: String, var type: String,var stu: String,var detail:String)
data class VideoUrl(var name:String,var url:String)