package com.test.animation.ui
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.animation.R
import com.test.animation.ui.adapters.ComicsAdapter
import com.test.animation.ui.adapters.SearchAdapter
import com.test.animation.ui.viewmodel.SearchViewModel

import com.test.animation.logic.model.picmodel.response.ComicListObj

import com.test.animation.logic.model.picmodel.response.SearchObj

import com.test.animation.logic.network.request.sortway


class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var comicslist:ArrayList<ComicListObj>
        var keyword=""
        val pref=getSharedPreferences("data",Context.MODE_PRIVATE)
        var token =pref.getString("key","")
        setContentView(R.layout.activity_search)
        var edit:EditText=findViewById(R.id.ssedit)
        var bar:Toolbar=findViewById(R.id.searchbar)
        val comic:RadioButton=findViewById(R.id.comic)
        val anima:RadioButton=findViewById(R.id.anima)
        setSupportActionBar(bar)
        var recyclerView:RecyclerView=findViewById(R.id.searchre)
            recyclerView.layoutManager=LinearLayoutManager(this)
            var quickadapter=ComicsAdapter(this,R.layout.item_comics,null)
            quickadapter.setEmptyView(R.layout.empty)
            recyclerView.adapter=quickadapter
        var searchbutton:ImageButton=findViewById(R.id.searchbut)
        var searching:ProgressBar=findViewById(R.id.searching)
        var viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        viewModel.wantthing.observe(this, Observer {
            if(it.size!=0) {
                searching.visibility=View.GONE
                recyclerView.adapter = SearchAdapter(it,this)
            }else{
                searching.visibility=View.GONE

                Toast.makeText(this,"没有找到",Toast.LENGTH_SHORT).show()

            }


        })
        viewModel.comiclst.observe(this, Observer {
            if(searching.visibility!=View.GONE)
                searching.visibility=View.GONE
            var rest=it.getOrNull()?.comics
            if(rest!=null&&rest.docs.size!=0){
                viewModel.page=rest.page
                viewModel.pages=rest.pages
                if(recyclerView.adapter is SearchAdapter)
                {

                    recyclerView.adapter=quickadapter
                }
               quickadapter.addData(rest.docs)
                viewModel.page++

            }

        })


        searchbutton.setOnClickListener {
            if(anima.isChecked){

                if(edit.text.isEmpty())
                {
                    Toast.makeText(this,"empty",Toast.LENGTH_SHORT).show()
                }else{
                    searching.visibility=View.VISIBLE
                    viewModel.Search("http://www.yhdm.so/search/"+edit.text)


                }


            }else{
                if(comic.isChecked){
                    if(edit.text.isEmpty())
                    {
                        Toast.makeText(this,"empty",Toast.LENGTH_SHORT).show()
                    }else{

                        if(token.isNullOrEmpty())
                        {
                            Toast.makeText(this,"no token",Toast.LENGTH_SHORT).show()

                        }else{
                            searching.visibility=View.VISIBLE
                            keyword=edit.text.toString()
                            viewModel.getcomics(SearchObj(token,viewModel.page,sortway(keyword,"dd")))



                        }

                    }

                }

            }

        }

quickadapter.setOnItemClickListener { adapter, view, position ->

    var bookid= quickadapter.data[position].comicId
    var intent= Intent(this,ComicDetailActivity::class.java)
    intent.putExtra("bookId",bookid)
    startActivity(intent)
}


        quickadapter.loadMoreModule.setOnLoadMoreListener{
            recyclerView.postDelayed({

                if(viewModel.page<=viewModel.pages)
                {
                    if(token!=null){
                      viewModel.getcomics(SearchObj(token,viewModel.page, sortway(keyword,"dd")))
                        quickadapter.loadMoreModule.loadMoreComplete()


                     }else{
                        quickadapter.loadMoreModule.loadMoreEnd()
                        return@postDelayed
                     }
                }else
                {
                    quickadapter.loadMoreModule.loadMoreEnd()
                    return@postDelayed


                }






            },3000)


        }





    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->onBackPressed()
        }
        return true
    }





}