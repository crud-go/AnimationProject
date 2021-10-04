package com.test.animation.ui.adapters

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.test.animation.R
import com.test.animation.logic.model.picmodel.response.ComicListObj

class ComicsAdapter(context: Context, layoutId:Int, data:MutableList<ComicListObj>? = null):BaseQuickAdapter<ComicListObj,BaseViewHolder>(layoutId,data),LoadMoreModule {

    override fun convert(holder: BaseViewHolder, item: ComicListObj) {
        var img=item.thumb.fileServer+"/static/"+item.thumb.path
        holder.setText(R.id.comic_title,item.title)
        holder.setText(R.id.comic_author,item.author)
        holder.setText(R.id.comic_type,item.categories.toString())
        Glide.with(context).load(img).into(holder.getView(R.id.comic_img) as ImageView)





    }



}