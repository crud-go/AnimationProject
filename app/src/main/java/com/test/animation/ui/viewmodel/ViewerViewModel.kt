package com.test.animation.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.test.animation.logic.model.picmodel.ComicPicBodyModel
import com.test.animation.logic.model.picmodel.ComicPictureModel
import com.test.animation.logic.repository.Respository

class ViewerViewModel:ViewModel() {
    var pic=MutableLiveData<ComicPicBodyModel>()
    fun getPicBody(model:ComicPicBodyModel){
        pic.value=model
    }
    var ComicBody=Transformations.switchMap(pic){
        pi->Respository.GetComicBody(pi.token,pi.bookid,pi.order,pi.page)
    }

    var picmo  =MutableLiveData<ComicPictureModel>()

    fun getPic(model:ComicPictureModel){
        picmo.value=model
    }

    var ComicsPic=Transformations.switchMap(picmo){
        picm->Respository.GetComicPicture(picm.token,picm.eps,picm.page)
    }
 var page:Int=1
    var pages:Int=1
}