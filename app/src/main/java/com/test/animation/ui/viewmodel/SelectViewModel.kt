package com.test.animation.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.test.animation.logic.repository.Respository

class SelectViewModel:ViewModel() {
 var Html=MutableLiveData<String>()
    var Url = MutableLiveData<String>()
    var vedios=Transformations.switchMap(Html){ html->
        Respository.getVedioUrl(html)
    }
    var result=Transformations.switchMap(Url){
        url->
        Respository.getHTML(url)
    }
    fun getHtml(url:String){

        Url.value=url
    }
    fun getVedio(HTML:String){
        Html.value=HTML
    }


}