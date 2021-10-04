package com.test.animation.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.test.animation.logic.model.picmodel.ComicleadersModel
import com.test.animation.logic.repository.Respository

class ComicRankViewmodel:ViewModel() {
    var ober_moel=MutableLiveData<ComicleadersModel>()
    var Comiclerrespon=Transformations.switchMap(ober_moel){
        ob->Respository.getLeaderBoard(ob.token,ob.param1,ob.param2)
    }

    fun getLeaderBoard(model:ComicleadersModel){
        ober_moel.value=model

    }


}