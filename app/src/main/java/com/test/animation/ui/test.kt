package com.test.animation.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.animation.AnimationApp


import com.test.animation.R
import com.test.animation.ui.viewmodel.SignInViewModel


import com.test.animation.logic.model.picmodel.SigninModel
import com.test.animation.logic.model.picmodel.response.*
import com.test.animation.logic.network.appdata.ServiceCreator

import retrofit2.*


class test : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val ips = arrayListOf("104.20.180.50", "104.20.181.50")
        AnimationApp.context.getSharedPreferences("data", 0).edit()
            .putStringSet("KEY_DNS_IP", HashSet(ips)).apply()

        val pref=getSharedPreferences("data",0)
        val isRemember=pref.getBoolean("IsRemember",false)
        val check:CheckBox=findViewById(R.id.isre)

        val button_log:Button=findViewById(R.id.button_login)
        var button_enter:Button=findViewById(R.id.button_inter)
        val edit_email:EditText=findViewById(R.id.edit_email)
        val edit_password:EditText=findViewById(R.id.edit_passw)
        if(isRemember){
            val myemail=pref.getString("email","")
            val mypassword=pref.getString("password","")
            edit_email.setText(myemail)
            edit_password.setText(mypassword)
            check.isChecked=true

        }
        var viewmodel=ViewModelProvider(this).get(SignInViewModel::class.java)
        viewmodel.auther.observe(this, Observer {
            var res=it.getOrNull()
            if(res?.data?.token!=null)
            {
                var token=res.data.token
              var pre=  getSharedPreferences("data",0).edit()
                pre.putString("key",token)
                if(check.isChecked)
                {
                    getSharedPreferences("data",0).edit{
                        putString("email",edit_email.text.toString())
                        putString("password",edit_password.text.toString())
                        putBoolean("IsRemember",true)
                    }


                }
                pre.apply()
                var intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()

            }else{
                Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show()

                it.exceptionOrNull()?.printStackTrace()
            }

        })

        button_log.setOnClickListener {
            var password=edit_password.text.toString()
            var email=edit_email.text.toString()
            if(password.isNotEmpty()&&email.isNotEmpty())
            {
                viewmodel.Signin_CAll(SigninModel(email,password))
            }else{
                Toast.makeText(this,"不能为空",Toast.LENGTH_SHORT).show()
            }


        }
        button_enter.setOnClickListener {
            var intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }






    }
}