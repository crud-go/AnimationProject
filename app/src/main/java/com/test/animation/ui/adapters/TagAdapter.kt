package com.test.animation.ui.adapters

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.test.animation.R

class TagAdapter(layoutId:Int, data:ArrayList<String>?=null):BaseQuickAdapter<String,BaseViewHolder>(layoutId,data) {
    override fun convert(holder: BaseViewHolder, item: String) {
      holder.setText(R.id.tag_text,item)
    }
}