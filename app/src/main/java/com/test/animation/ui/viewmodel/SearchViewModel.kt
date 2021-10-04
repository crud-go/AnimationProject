package com.test.animation.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.test.animation.logic.model.picmodel.response.SearchObj
import com.test.animation.logic.repository.Respository

class SearchViewModel :ViewModel(){
    var page:Int=1
    var pages:Int=1
    var something=MutableLiveData<String>()
    var sobj= MutableLiveData<SearchObj>()

    var wantthing=Transformations.switchMap(something){
        someurl->Respository.getSearch(someurl)
    }
fun Search(thing:String)
{
    something.value=thing

}
 var comiclst=Transformations.switchMap(sobj){
     sobj->Respository.getComics(sobj.token,sobj.page,sobj.Sort)
 }

    fun getcomics(model:SearchObj){

     sobj.value=model
    }


}