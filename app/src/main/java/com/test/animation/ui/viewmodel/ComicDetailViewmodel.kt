package com.test.animation.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.test.animation.logic.model.picmodel.BookInfoModel
import com.test.animation.logic.model.picmodel.ComicExpModel
import com.test.animation.logic.repository.Respository

class ComicDetailViewmodel:ViewModel() {
    var comic_img=MutableLiveData("")
    var title=MutableLiveData("")
    var auther=MutableLiveData("")
    var team=MutableLiveData("")
    var viewtimes=MutableLiveData("")
    var category=MutableLiveData("")
    var description=MutableLiveData("")
    var comicid=""

    var bookid=MutableLiveData<BookInfoModel>()
    var bookexp= MutableLiveData<ComicExpModel>()
    fun getComicinFo(model:BookInfoModel){
        bookid.value=model
    }
    var book_info=Transformations.switchMap(bookid){
        id->Respository.GetComicInfo(id.token,id.id)
    }

    var episode=Transformations.switchMap(bookexp){
        exp->Respository.GetComicEXP(exp.toke,exp.path,exp.page)
    }

    fun getExp(model:ComicExpModel){
        bookexp.value=model

    }




}