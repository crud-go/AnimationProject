package com.test.animation.logic.network.appdata

import com.test.animation.logic.model.picmodel.SigninModel
import com.test.animation.logic.model.picmodel.response.*
import com.test.animation.logic.network.request.sortway
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ComicNetwork {
    var my_interface:PicaData


    init {
       my_interface =ServiceCreator().inter_face
    }
    suspend fun getLeaderBoard(token: String,str1:String,Str2:String):GeneralResponse<LeaderboardResponse>
    =my_interface.getleader(token,str1,Str2).await()
    suspend fun getComicBody(token: String,bookid: String,order:Int,page:Int):GeneralResponse<ComicPagesResponse>
    =my_interface.getComicbody(token,bookid,order,page).await()
    suspend fun geteposide(token: String,path:String,page:Int):GeneralResponse<ComicEpisodeResponse>
    =my_interface.getepisode(token,path,page).await()
    suspend fun getComicinfo(token: String,bookid:String):GeneralResponse<ComicDetailResponse> =
    my_interface.getComicInfo(token,bookid).await()
     suspend fun GetComicList(token :String,page:Int,sort:sortway):GeneralResponse<ComicListResponse>
   =my_interface.search(token,page,sort).await()
    suspend fun getComicPicture(token: String,epsid:String,page: Int):GeneralResponse<ComicPagesResponse>
    =my_interface.getComicPicture(token,epsid,page).await()

    suspend fun SignIN(email:String,password:String)=
        my_interface.SignIN(SigninModel(email,password)).await()

    private suspend fun <T> Call<T>.await():T{
        return suspendCoroutine { continuation ->
            enqueue(object :Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                  val body=response.body()
                    if (body!=null){
                        continuation.resume(body)

                    }else{
                        continuation.resumeWithException(RuntimeException("response is null"))
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

            })
        }
    }


}