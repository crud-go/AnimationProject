package com.test.animation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.animation.R
import com.test.animation.logic.model.picmodel.ComicPicBodyModel
import com.test.animation.logic.model.picmodel.ComicPictureModel
import com.test.animation.ui.adapters.ViewerAdapter
import com.test.animation.ui.viewmodel.ViewerViewModel

class ViewerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_viewer)
        var bookid=intent.getStringExtra("bookid")
        var order=intent.getIntExtra("order",0)
        var token=getSharedPreferences("data",0).getString("key","")
        var adapter=ViewerAdapter(R.layout.item_viewer,null)
        var bar:Toolbar=findViewById(R.id.viewbar)
        setSupportActionBar(bar)
        adapter.setEmptyView(R.layout.empty)
        var viewer:RecyclerView=findViewById(R.id.viewer_recycler)
        viewer.layoutManager=LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        viewer.adapter=adapter
        var viewmodel=ViewModelProvider(this).get(ViewerViewModel::class.java)

       if(token!=null&&bookid!=null&&order!=0)
       {
           viewmodel.getPicBody(ComicPicBodyModel(token,bookid,order,1))
       }

        viewmodel.ComicBody.observe(this, Observer {
            var gets=it.getOrNull()
            if(gets!=null)
            {
                viewmodel.page=gets.pages.page
                viewmodel.pages=gets.pages.pages
                var pics=gets.pages.docs
                adapter.addData(pics)
                viewmodel.page++

            }
        })

        adapter.loadMoreModule.setOnLoadMoreListener {
            viewer.postDelayed({
                if(token!=null&&bookid!=null&&order!=0){

                    if (viewmodel.page <= viewmodel.pages) {
                        viewmodel.getPicBody(ComicPicBodyModel(token,bookid,order,viewmodel.page))
                        adapter.loadMoreModule.loadMoreComplete()
                }else{

                        adapter.loadMoreModule.loadMoreEnd()
                        return@postDelayed
                }



                }else{

                    adapter.loadMoreModule.loadMoreEnd()
                    return@postDelayed
                }
            }, 3000)
        }



    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }
}