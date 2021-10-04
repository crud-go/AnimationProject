package com.test.animation.ui.adapters

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.test.animation.R
import com.test.animation.logic.model.picmodel.response.ComicEpisodeObject

class EpisodeAdapter(layoutid:Int,data:ArrayList<ComicEpisodeObject>?=null):BaseQuickAdapter<ComicEpisodeObject,BaseViewHolder>(layoutid,data) {
    override fun convert(holder: BaseViewHolder, item: ComicEpisodeObject) {
        holder.setText(R.id.esp_text,item.title)
    }
}