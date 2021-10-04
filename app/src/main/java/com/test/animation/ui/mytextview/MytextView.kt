package com.test.animation.ui.mytextview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.test.animation.R
import kotlin.properties.Delegates

class MytextView(context: Context):LinearLayout(context) {

    lateinit var mytextView:TextView
    lateinit var myImageView: ImageView
    private var lineCount=0
    var maxLine=3
    var isopen=false
    constructor(context: Context,attrs:AttributeSet):this(context){

    }
    constructor(context: Context,attrs: AttributeSet,sty:Int):this(context){



    }
fun initial(){
    lineCount=mytextView.lineCount
    if(lineCount<=maxLine)
    {
   myImageView.visibility=View.GONE
    }
    if(isopen&&mytextView.height!=lineCount*mytextView.lineHeight){
        mytextView.height=mytextView.lineHeight*mytextView.lineCount
    }else if(isopen&&mytextView.height!=maxLine*mytextView.lineHeight){
        mytextView.height=mytextView.lineHeight*maxLine

    }

    myImageView.setOnClickListener {
        if(isopen){
            mytextView.height=mytextView.lineHeight*maxLine
            myImageView.setImageResource(R.drawable.sharp_expand_more_black_18pt_1x)
            isopen=false


        }else{
            mytextView.height=mytextView.lineHeight*mytextView.lineCount
            myImageView.setImageResource(R.drawable.sharp_expand_less_black_18pt_1x)
            isopen=true

        }


    }


}
    //if(isOpen && mTextView.getHeight() != lineCounts * mTextView.getLineHeight()){
   //     mTextView.setHeight(mTextView.getLineHeight() * mTextView.getLineCount());
   // }else if(!isOpen && mTextView.getHeight() != foldLines * mTextView.getLineHeight()){
  //      mTextView.setHeight(mTextView.getLineHeight() * foldLines);
  //  }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        initial()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

           var mTextView = getChildAt(0) as TextView
           var  mOpenBtn = getChildAt(1) as ImageView


    }


}