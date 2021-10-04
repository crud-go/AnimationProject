package com.test.animation.ui.adapters

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.test.animation.R
import com.test.animation.logic.model.picmodel.response.LeaderboardComicListObject

class QuickComicRank(layoutid:Int,data:ArrayList<LeaderboardComicListObject>?=null):BaseQuickAdapter<LeaderboardComicListObject,BaseViewHolder>(layoutid,data) {
    override fun convert(holder: BaseViewHolder, item:LeaderboardComicListObject) {
      holder.setText(R.id.comic_title_rank,item.title)
       holder.setText(R.id.comic_author_rank,item.author)
        holder.setText(R.id.comic_type_rank,item.categories.toString())
       var img =item.thumb.fileServer+"/static/"+item.thumb.path
        Glide.with(context).load(img).into(holder.getView(R.id.comic_img_rank) as ImageView)

    }
}