package com.test.animation.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.animation.AnimationApp
import com.test.animation.R
import com.test.animation.ui.PlayActivity
import com.test.animation.logic.model.AnimationWeek

class RankAdapter(var list:MutableList<AnimationWeek>,var context: FragmentActivity?): RecyclerView.Adapter<RankAdapter.ViewHolder>(){

    class ViewHolder(v: View):RecyclerView.ViewHolder(v){
        var tite:TextView
        var staut:TextView
        var type:TextView
        var img:ImageView
        init {
            tite=v.findViewById(R.id.title)
            staut=v.findViewById(R.id.staut)
           type=v.findViewById(R.id.type)
            img=v.findViewById(R.id.animg)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rank, parent, false)
        var viewholder=ViewHolder(view)









        return viewholder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tite.text=list[position].name
        Glide.with(AnimationApp.context).load(list[position].img_url).into(  holder.img)
        holder.staut.text=list[position].stu
        holder.type.text=list[position].type
        holder.itemView.setOnClickListener {
            var parturl = list[position].detail
            val intent = Intent(context, PlayActivity::class.java)
            intent.putExtra("part", parturl)

           context!!.startActivity(intent)


        }


    }

    override fun getItemCount(): Int =list.size


}