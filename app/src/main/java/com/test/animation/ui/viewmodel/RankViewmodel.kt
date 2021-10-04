package com.test.animation.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.test.animation.logic.repository.Respository

class RankViewmodel:ViewModel() {
    private val searchurl = MutableLiveData<String>()
    private var htl = MutableLiveData<String>()

    var html= Transformations.switchMap(searchurl){
            url->
        Respository.getHTML(url)
    }

    val AniamList = Transformations.switchMap(htl){
            html->
        Respository.getWeekRank(html)
    }
    fun getHtMl(url:String){
        searchurl.value=url
    }
    fun getWeekRank(htls:String){
        htl.value=htls

    }

}