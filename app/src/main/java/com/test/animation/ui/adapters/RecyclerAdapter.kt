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
import com.test.animation.logic.model.AnimationProfile

class RecyclerAdapter(var mutableList:MutableList<AnimationProfile>,var context: FragmentActivity?): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>()  {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textView: TextView
        var imageView: ImageView


        init {
            textView = view.findViewById(R.id.textview)
            imageView=view.findViewById(R.id.img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_animation, parent, false)
        val viewholder=ViewHolder(view)


        return viewholder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text=mutableList[position].name
        Glide.with(AnimationApp.context).load(mutableList[position].img_url).into(holder.imageView)
        holder.itemView.setOnClickListener {
            var parturl=mutableList[position].detail
            val intent = Intent(context,PlayActivity::class.java).apply{
                putExtra("part",parturl)
            }
            context!!.startActivity(intent)

        }

    }

    override fun getItemCount():Int=mutableList.size


}