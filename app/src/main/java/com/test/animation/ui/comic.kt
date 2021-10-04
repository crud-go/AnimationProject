package com.test.animation.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.animation.R
import com.test.animation.logic.model.picmodel.ComicleadersModel
import com.test.animation.ui.adapters.QuickComicRank
import com.test.animation.ui.adapters.RankAdapter
import com.test.animation.ui.viewmodel.ComicRankViewmodel
import com.test.animation.ui.viewmodel.RankViewmodel

class comic : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var v= inflater.inflate(R.layout.fragment_comic, container, false)
        var recycler: RecyclerView = v.findViewById(R.id.comicrand)
        var token=context?.getSharedPreferences("data",0)?.getString("key","")
        //val layoutManager= StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recycler.layoutManager= LinearLayoutManager(context)
       var quickada=QuickComicRank(R.layout.item_comic_rank,null)
        recycler.adapter=quickada
        quickada.setOnItemClickListener { adapter, view, position ->
            var bookid= quickada.data[position].comicId
            var intent= Intent(context,ComicDetailActivity::class.java)
            intent.putExtra("bookId",bookid)
            startActivity(intent)
        }

        var viewModel = ViewModelProvider(this).get(ComicRankViewmodel::class.java)
        if(token!=null){
            viewModel.getLeaderBoard(ComicleadersModel(token,"H24","VC"))
        }

        viewModel.Comiclerrespon.observe(viewLifecycleOwner, Observer {
            var res=it.getOrNull()
            if(res!=null){
                quickada.addData(res.comics)

            }else{
                it.exceptionOrNull()?.printStackTrace()
            }
        })

        return v

    }


}