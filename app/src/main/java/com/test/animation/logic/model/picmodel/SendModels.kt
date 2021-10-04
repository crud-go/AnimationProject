package com.test.animation.logic.model.picmodel

  class RegisterModel(
    val answer1:String,
    val answer2: String,
    val answer3: String,
    val birthday:String,
    val email:String,
    val gender:String,
    val password:String,
    val question1:String,
    val question2: String,
    val question3: String,
    )

class SigninModel(
    var email: String,
    var password: String
)
class ResetPasswordModel(
   val answer1: String,
   val email: String,
   val isRight:Boolean
)
class RegisterResponses(
    val message:String,
    val code:Int
)
class BookInfoModel(
    val token:String,
    val id:String
)
class ComicExpModel(
    val toke: String,
    val path:String,
    val page:Int
)
class ComicPictureModel(
    val token: String,
    val eps:String,
    val page: Int
)
class ComicPicBodyModel(
    val token: String,
    val bookid:String,
    val order:Int,
    val page:Int
)
class ComicleadersModel(
    val token: String,
    val param1:String,
    val param2:String
)

