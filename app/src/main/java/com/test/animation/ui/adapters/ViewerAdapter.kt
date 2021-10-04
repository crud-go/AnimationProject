package com.test.animation.ui.adapters


import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.test.animation.R
import com.test.animation.logic.model.picmodel.response.ComicPageObject


class ViewerAdapter(layoutid:Int, data:ArrayList<ComicPageObject>?=null):BaseQuickAdapter<ComicPageObject,BaseViewHolder>(layoutid,data),LoadMoreModule {
    override fun convert(holder: BaseViewHolder, item: ComicPageObject) {
       var img= item.media.fileServer+"/static/"+item.media.path
        Glide.with(context).load(img).placeholder(R.drawable.icon_empty).into(holder.getView(R.id.picture) as ImageView)

    }

}