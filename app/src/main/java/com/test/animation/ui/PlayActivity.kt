package com.test.animation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.test.animation.R
import com.test.animation.ui.adapters.SelectAdapter
import com.test.animation.ui.viewmodel.SelectViewModel


import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar

import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer


class PlayActivity : AppCompatActivity() {

    lateinit var GSY:StandardGSYVideoPlayer
    lateinit var orenta:OrientationUtils
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        var part=intent.getStringExtra("part")
        var URL="http://www.yhdm.so$part"
        var Re_episode:RecyclerView=findViewById(R.id.re_episode)
        var Pro:ProgressBar=findViewById(R.id.pr)
        var toolbar:androidx.appcompat.widget.Toolbar=findViewById(R.id.too)
        setSupportActionBar(toolbar)
        GSY  = findViewById(R.id.GSY)
        GSY.backButton.setOnClickListener {
            onBackPressed()
        }

        orenta=OrientationUtils(this,GSY)
        GSY.fullscreenButton.setOnClickListener{
         GSY.startWindowFullscreen(this,true,true)

        }
        Re_episode.layoutManager= StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        var viewModel = ViewModelProvider(this).get(SelectViewModel::class.java)
        viewModel.result.observe(this, Observer { result->
            var HTML=result.toString()
            if (HTML.isNotEmpty())
                viewModel.getVedio(HTML)


        })
        viewModel.vedios.observe(this, Observer { result->


            var da= SelectAdapter(result,GSY,this)
            Re_episode.adapter=da
            Pro.visibility=View.GONE




        })

        viewModel.getHtml(URL)
        /*val uri: Uri = Uri.parse("https://v.hoopchina.com.cn/bbs-editor-web/editor/1631511389991/9Bc_2BKWTl4LN2YESqoUNxg2uVFQc_3D.mp4?auth_key=1631617826-0-0-5c718f645da1ad45e41106ff2c658a4a")

        videoView.setMediaController(MediaController(this))
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()*/

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->onBackPressed()
        }
        return true
    }

    override fun onPause() {
        super.onPause()
       GSY.onVideoPause()
    }
    override fun onResume() {
        super.onResume()
       GSY.onVideoResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
       orenta.releaseListener()


    }

    override fun onBackPressed() {
        if (GSYVideoManager.backFromWindowFull(this)) {
            return;
        }
        //释放所有
        GSY.setVideoAllCallBack(null);
        super.onBackPressed()
    }
}