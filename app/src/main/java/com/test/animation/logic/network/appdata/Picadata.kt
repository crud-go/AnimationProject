package com.test.animation.logic.network.appdata


import com.test.animation.logic.model.picmodel.RegisterModel
import com.test.animation.logic.model.picmodel.RegisterResponses
import com.test.animation.logic.model.picmodel.SigninModel
import com.test.animation.logic.model.picmodel.response.*

import com.test.animation.logic.network.request.sortway
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import com.test.animation.logic.model.picmodel.response.GeneralResponse

import retrofit2.http.GET




public interface PicaData {
     @POST("auth/register")
    fun Register(@Body paramRegisterBody: RegisterModel): Call<RegisterResponses>//注册

     @POST("auth/sign-in")
    fun SignIN(
        @Body paramSignInBody: SigninModel
     ): Call< GeneralResponse<SignInResponse>>//登录



    @POST("comics/advanced-search")//搜索
    fun search(
        @Header("authorization") paramString: String,
        @Query("page") paramInt: Int,
        @Body paramSortingBody: sortway
    ): Call<GeneralResponse<ComicListResponse>>


    @GET("keywords")
   fun keyword(@Header("authorization")  paramString:String):Call<GeneralResponse<KeywordsResponse>>


    @GET("init?platform=android")
    fun  inits(@Header("authorization") str: String): Call<GeneralResponse<InitialResponse>>


    @GET("comics/{comicId}")
    fun getComicInfo(@Header("authorization") paramString1:String, @Path("comicId") paramString2:String): Call<GeneralResponse<ComicDetailResponse>>
    @GET("comics/{comicId}/eps")
    fun getepisode(@Header("authorization") token:String , @Path("comicId") path: String, @Query("page")  param:Int):Call<GeneralResponse<ComicEpisodeResponse>>
    @GET("eps/{epsId}/pages")
   fun getComicPicture(@Header("authorization") param1:String, @Path("epsId") espId:String, @Query("page")page:Int): Call<GeneralResponse<ComicPagesResponse>>
    @GET("comics/{comicId}/order/{order}/pages")
    fun getComicbody(@Header("authorization") token:String, @Path("comicId") BookId:String, @Path("order") order:Int, @Query("page")page:Int):Call<GeneralResponse<ComicPagesResponse>>

    @GET("comics/leaderboard")
   fun getleader(@Header("authorization") token: String, @Query("tt") str: String, @Query("ct") str2: String): Call<GeneralResponse<LeaderboardResponse>>

}