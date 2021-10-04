package com.test.animation.ui


import android.content.Intent
import android.os.*
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.animation.R
import com.test.animation.ui.viewmodel.ComicDetailViewmodel
import com.test.animation.databinding.ActivityComicDetailBinding
import com.test.animation.logic.model.picmodel.BookInfoModel
import com.test.animation.logic.model.picmodel.ComicExpModel
import com.test.animation.ui.adapters.EpisodeAdapter
import com.test.animation.ui.adapters.TagAdapter

class ComicDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
     var bookid= intent.getStringExtra("bookId")
        var token=getSharedPreferences("data",0).getString("key","")
        super.onCreate(savedInstanceState)

        val binding :ActivityComicDetailBinding=
            DataBindingUtil.setContentView(this, R.layout.activity_comic_detail)
        binding.lifecycleOwner = this
        binding.TagRecycler.layoutManager=LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        var tagAdapter=TagAdapter(R.layout.item_tab,null)
        binding.TagRecycler.adapter=tagAdapter
        var  re= binding.episodeRecycler
        re.layoutManager=LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
        var epsadapter=EpisodeAdapter(R.layout.item_esp,null)
        re.adapter=epsadapter


        var viewmodel=ViewModelProvider(this).get(ComicDetailViewmodel::class.java)
          binding.model=viewmodel
        viewmodel.book_info.observe(this, Observer {
            var res=it.getOrNull()
            if(res!=null)
            {
                viewmodel.comicid=res.comic.comicId
                viewmodel.auther.value="作者:"+res.comic.author
                viewmodel.team.value="汉化:"+res.comic.chineseTeam
                viewmodel.title.value=res.comic.title
                viewmodel.category.value="分类:"+res.comic.categories.toString()
                viewmodel.viewtimes.value="观看次数:"+res.comic.viewsCount.toString()
                viewmodel.description.value=res.comic.description
                viewmodel.comic_img.value=res.comic.thumb.fileServer+"/static/"+res.comic.thumb.path
                tagAdapter.addData(res.comic.tags)

            }else{
                it.exceptionOrNull()?.printStackTrace()
                //Log.d("fail", "last step fail")

            }
        })
        viewmodel.episode.observe(this, Observer {
            var res2=it.getOrNull()
            if(res2!=null){
                epsadapter.addData(res2.eps.docs)


            }
        })



        if(bookid!=null&&token!=null){
            viewmodel.getComicinFo(BookInfoModel(token,bookid))
            viewmodel.getExp(ComicExpModel(token,bookid,1))

        }
        binding.startViewer.setOnClickListener {
            if(viewmodel.comicid.isNotBlank()) {
                var intent = Intent(this, ViewerActivity::class.java)
                intent.putExtra("bookid", viewmodel.comicid)
                intent.putExtra("order", 1)
                startActivity(intent)
            }else{

                Toast.makeText(this,"获取失败",Toast.LENGTH_SHORT).show()
            }


        }
       epsadapter.setOnItemClickListener { adapter, view, position ->
           if(viewmodel.comicid.isNotBlank()) {
               var intent = Intent(this, ViewerActivity::class.java)
               intent.putExtra("bookid", viewmodel.comicid)
               intent.putExtra("order", position+1)
               startActivity(intent)
           }else{

               Toast.makeText(this,"获取失败",Toast.LENGTH_SHORT).show()
           }


        }











    }
}