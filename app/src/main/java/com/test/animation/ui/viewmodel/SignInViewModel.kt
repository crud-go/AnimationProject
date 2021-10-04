package com.test.animation.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.test.animation.logic.model.picmodel.SigninModel
import com.test.animation.logic.repository.Respository

class SignInViewModel:ViewModel() {
    var sign_model=MutableLiveData<SigninModel>()
    var auther=Transformations.switchMap(sign_model){
        ssign->Respository.Signin_res(ssign.email,ssign.password)
    }
    fun Signin_CAll(model:SigninModel){
        sign_model.value=model
    }



}