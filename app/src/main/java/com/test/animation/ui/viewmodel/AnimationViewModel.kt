package com.test.animation.ui.viewmodel

import androidx.lifecycle.*


import com.test.animation.logic.repository.Respository


class AnimationViewModel:ViewModel() {
  private val searchurl =MutableLiveData<String>()
  private var htl =MutableLiveData<String>()

    var html= Transformations.switchMap(searchurl){
        url->Respository.getHTML(url)
    }

  val AniamList =Transformations.switchMap(htl){
    html->Respository.getAnimationProfileList(html)
  }
    fun getHtMl(url:String){
        searchurl.value=url
    }
fun getAniProfile(htls:String){
     htl.value=htls

}




}