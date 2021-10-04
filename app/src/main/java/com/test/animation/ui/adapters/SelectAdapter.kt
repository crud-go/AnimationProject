package com.test.animation.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
import com.test.animation.R
import com.test.animation.logic.model.VideoUrl

 class SelectAdapter(var vedio:MutableList<VideoUrl>,var GYS:StandardGSYVideoPlayer,var context: Context) :RecyclerView.Adapter<SelectAdapter.ViewHolder>(){
    class ViewHolder(v: View):RecyclerView.ViewHolder(v){
        var test:TextView
        init {
            test=v.findViewById(R.id.episode)

        }
    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val view = LayoutInflater.from(parent.context).inflate(R.layout.item_episode, parent, false)
         var viewholder= ViewHolder(view)





         return viewholder
     }

     override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         holder.test.text=vedio[position].name
         holder.itemView.setOnClickListener {


             GYS.setUp(vedio[position].url,true,vedio[position].name)
             GYS.startButton.performClick()


         }



     }

     override fun getItemCount(): Int =vedio.size


 }