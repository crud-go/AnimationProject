package com.test.animation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.test.animation.ui.SearchActivity
import com.test.animation.ui.adapters.RecyclerAdapter
import com.test.animation.ui.viewmodel.AnimationViewModel

class home : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


       var v= inflater.inflate(R.layout.fragment_home, container, false)
       var recycler:RecyclerView= v.findViewById(R.id.recycler)
        var refresh:SwipeRefreshLayout=v.findViewById(R.id.swipe)
        var fot:FloatingActionButton=v.findViewById(R.id.fab_search)
        fot.setOnClickListener {
            var intent = Intent(activity,SearchActivity::class.java)
            startActivity(intent)
        }

       // val layoutManager= StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        //recycler.layoutManager=layoutManager
         recycler.layoutManager= GridLayoutManager(context,3)
        var viewModel = ViewModelProvider(this).get(AnimationViewModel::class.java)
        viewModel.html.observe(viewLifecycleOwner, Observer { result->
            var HTML=result.toString()
            if (HTML!=null)
            viewModel.getAniProfile(HTML)


        })
        viewModel.AniamList.observe(viewLifecycleOwner, Observer { result->


            var da=RecyclerAdapter(result,activity)
            
            recycler.adapter=da


        })



        viewModel.getHtMl("http://www.yhdm.so")
        refresh.setOnRefreshListener {
    viewModel.getHtMl("http://www.yhdm.so")

    }




        return v
    }


}