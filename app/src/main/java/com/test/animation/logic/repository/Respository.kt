package com.test.animation.logic.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.test.animation.logic.model.AnimationProfile
import com.test.animation.logic.model.VideoUrl
import com.test.animation.logic.model.picmodel.response.ComicListResponse
import com.test.animation.logic.network.AnimationPrase
import com.test.animation.logic.network.appdata.ComicNetwork
import com.test.animation.logic.network.request.sortway
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import kotlin.RuntimeException

object Respository {
fun getHTML(url: String) = liveData(Dispatchers.IO){
    val result=try {
        val ht =AnimationPrase.getHtml(url)
        if (ht!=null)
            Result.success(ht)
        else
            Result.failure(RuntimeException("this is error"))
    }catch (e:Exception){
        Result.failure(e)

    }
    emit(result)


}
    fun getAnimationProfileList(html:String)=liveData(Dispatchers.IO){


        var list=AnimationPrase.PraseDaliy(html)
        if(list.size!=0)
        {
            emit(list)
        }

    }
    fun getWeekRank(html: String)= liveData(Dispatchers.IO){

        var list=AnimationPrase.PraseWeekRank(html)
        if(list.size!=0)
        {
            emit(list)
        }



    }
    fun getVedioUrl(html: String)= liveData(Dispatchers.IO){
        AnimationPrase.getAllVedioUrl(html,object :AnimationPrase.callmysu{
            override suspend  fun onSuccess(ls: MutableList<VideoUrl>) {
                emit(ls)
            }

        })


    }
    fun getSearch(url:String)=liveData(Dispatchers.IO){
        var list =AnimationPrase.PraseSearch(url)
        emit(list)


    }
    fun getComics(token:String,page:Int,sort:sortway)=liveData(Dispatchers.IO){
        val result=try {
            val comicResponse=ComicNetwork().GetComicList(token,page,sort)
            if(comicResponse.code==200){
                val comics=comicResponse.data
                Result.success(comics)
            }else{
                Result.failure(RuntimeException("response status is ${comicResponse.code}"))
            }
        }catch (e:Exception){
            Result.failure(e)
        }
        emit(result)

    }
    fun Signin_res(email:String,password:String)=liveData(Dispatchers.IO){

        val result=try {
            val signinresponse=ComicNetwork().SignIN(email,password)
            if(signinresponse.code==200){

                Result.success(signinresponse)
            }else{
                Result.failure(RuntimeException("no token is valid"))
            }
        }catch (e:Exception){
            Result.failure(e)
        }
        emit(result)

    }


    fun GetComicInfo(token:String,book:String)=liveData(Dispatchers.IO){
        val result=try{
            val Info=ComicNetwork().getComicinfo(token,book)
            if(Info.code==200){
                val data=Info.data
                Result.success(data)
            }else{
                Result.failure(RuntimeException("get info fail"))
            }
        }catch (e:Exception)
        {
            Result.failure(e)
        }
        emit(result)

    }
    fun GetComicEXP(token: String,path:String,page: Int)=liveData(Dispatchers.IO){
        val result=try{
            val res=ComicNetwork().geteposide(token,path,page)
            if(res.code==200){
                val data=res.data
                Result.success(data)
            }else{
                Result.failure(RuntimeException("get exp,fail"))
            }

        }catch (e:Exception)
        {
            Result.failure(e)
        }
        emit(result)
    }
    fun GetComicPicture(token: String,epsid:String,page: Int)= liveData(Dispatchers.IO) {
        val result=try{
            val res=ComicNetwork().getComicPicture(token,epsid,page)
            if(res.code==200)
            {
                val data=res.data
                Result.success(data)
            }else{
                Result.failure(java.lang.RuntimeException("get picture fail"))
            }
        }catch (e:Exception){
            Result.failure(e)
        }
        emit(result)
    }
    fun GetComicBody(token: String,book: String,order:Int,page: Int)= liveData(Dispatchers.IO) {

        val result=try{
            val res=ComicNetwork().getComicBody(token,book,order,page)
            if(res.code==200)
            {
                val data=res.data
                Result.success(data)
            }else{
                Result.failure(java.lang.RuntimeException("get picture body fail"))
            }
        }catch (e:Exception){
            Result.failure(e)
        }
        emit(result)

    }
    fun getLeaderBoard(token: String,str1:String,Str2:String)= liveData(Dispatchers.IO){

        val result=try{
            val res=ComicNetwork().getLeaderBoard(token,str1,Str2)
            if(res.code==200)
            {
                val data=res.data
                Result.success(data)
            }else{
                Result.failure(java.lang.RuntimeException("get picture body fail"))
            }
        }catch (e:Exception){
            Result.failure(e)
        }
        emit(result)



    }



}