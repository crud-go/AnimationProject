package com.test.animation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.animation.ui.adapters.RankAdapter
import com.test.animation.ui.viewmodel.RankViewmodel


class rank : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var v= inflater.inflate(R.layout.fragment_rank, container, false)
        var recycler: RecyclerView = v.findViewById(R.id.rank)

        //val layoutManager= StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recycler.layoutManager=LinearLayoutManager(context)

        var viewModel = ViewModelProvider(this).get(RankViewmodel::class.java)

        viewModel.html.observe(viewLifecycleOwner, Observer { result->
            var HTML=result.getOrNull()
            if (HTML!=null)
                viewModel.getWeekRank(HTML)


        })
        viewModel.AniamList.observe(viewLifecycleOwner, Observer { result->



            recycler.adapter= RankAdapter(result,activity)




        })

        viewModel.getHtMl("http://www.yhdm.so")





        return v
    }


}