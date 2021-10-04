package com.test.animation.logic.network
import com.test.animation.logic.model.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.*
import org.jsoup.Jsoup
import java.io.IOException
import java.util.regex.Pattern
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object AnimationPrase {

   suspend fun getHtml(url:String):String{
       val client = OkHttpClient()
       val request = Request.Builder()
           .url(url)
           .build()
           return suspendCoroutine { continuation ->
               client.newCall(request).enqueue(object : Callback {
                   override fun onFailure(call: Call, e: IOException) {
                       continuation.resumeWithException(e)

                   }

                   override fun onResponse(call: Call, response: Response) {
                       continuation.resume(response.body()!!.string())

                       }

               })
           }
   }
    fun PraseDaliy(html:String):MutableList<AnimationProfile>{
        var animationlist:MutableList<AnimationProfile> = mutableListOf()

      var doc = Jsoup.parse(html)
        var elements =doc.select("div.img>ul>li")
        for(el in elements) {

          var src= el.select("img").attr("src")
          var alt=  el.select("img").attr("alt")
            var del=el.select("a").attr("href")
            var ps: AnimationProfile=AnimationProfile(src,alt,del)
            animationlist.add(ps)

        }
        return animationlist


    }
    fun PraseWeekRank(html:String):MutableList<AnimationWeek>{
        var animationlist:MutableList<AnimationWeek> = mutableListOf()

        var doc = Jsoup.parse(html)
        var elements=doc.select("div.pics>ul>li")
        for(el in elements)
        {
            var detail=el.select("a").attr("href")
            var name =el.select("img").attr("alt")
            var img=el.select("img").attr("src")
            var type=el.select("span>a").text()
            var staut=el.select("span>font").text()
            var week=AnimationWeek(img,name,type,detail,staut)
            animationlist.add(week)


        }
        return animationlist

    }
   /* fun PraseWeeklist(html:String):MutableList<MutableList<AnimationRank>>{
        var animationlist:MutableList<MutableList<AnimationRank>> = mutableListOf()
        var everyday: MutableList<AnimationRank> = mutableListOf()
        var doc = Jsoup.parse(html)
        var elements=doc.select("div.pics>ul")
        for(el in elements){
            var li =el.select("li")
            for(ss in li){
               var detail= ss.select("a").attr("href")
                var name =ss.select("a").attr("title")
                everyday.add(AnimationRank(detail,name))

            }
            animationlist.add(everyday)



        }

     return  animationlist

    }*/
   suspend fun PraseSearch(url:String):MutableList<SearchResult>
   {    var html= getHtml(url)
        var result:MutableList<SearchResult> = mutableListOf()
        var doc = Jsoup.parse(html)
        var topic= doc.select("div.lpic>ul>li")
        if (topic.isEmpty())
        {
            return result
            //没找到
        }else{
            var pages=doc.select("div.pages>a")
            if(pages.isEmpty())
            {//只有一页
                var els= doc.select("div.lpic>ul>li")
                for(el in els)
                {
                    var detail=el.select("a").attr("href")
                    var img =el.select("img").attr("src")
                    var name=el.select("img").attr("alt")
                    var stu =el.select("span>font").text()
                    var type =el.select("span>a").text()
                    var se=SearchResult(name,img,type,stu,detail)
                    result.add(se)

                }
                return  result

            }else{
                //有好多页
                val max=pages.size-2
                var i=1
                while(i<=max)
                {
                    var geets= getHtml(url+"?page=${i}")
                    var docs=Jsoup.parse(geets)
                    var elss= docs.select("div.lpic>ul>li")
                    for(el in elss)
                    {
                        var detail=el.select("a").attr("href")
                        var img =el.select("img").attr("src")
                        var name=el.select("img").attr("alt")
                        var stu =el.select("span>font").text()
                        var type =el.select("span>a").text()
                        var se=SearchResult(name,img,type,stu,detail)
                        result.add(se)

                    }
                    i+=1


                }
                return result



            }

        }
    }
    interface callmysu{
      suspend  fun onSuccess(ls:MutableList<VideoUrl>)
    }


   suspend fun getAllVedioUrl(Html:String,ca:callmysu)= coroutineScope {
       var anima_url:String=""
       var anima_name:String=""
       val PLAY_URL_PATTERN: Pattern = Pattern.compile("url: \"(https?|ftp|file):\\/\\/[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]")
        var Videolist:MutableList<VideoUrl> = mutableListOf()



       var doc=Jsoup.parse(Html)
        var els= doc.select("div.movurl>ul>li")


        for (el in els)
        {


            launch {

            anima_url=""

           var del=  el.select("a").attr("href")
            anima_name=el.select("a").text()

            //获取到name
            //mutableList.add()

            var hts= getHtml("http://www.yhdm.so$del")
            var doc2 =Jsoup.parse(hts)
            var realurl= "http://tup.yhdm.so/?vid="+doc2.select("div.bofang>div").attr("data-vid")
            var hts2 = getHtml(realurl)
            var doc3 =Jsoup.parse(hts2)
            var fakeurl=doc3.select("iframe").attr("src")

            if(fakeurl.isBlank())

            {
                var cps=doc3.select("script")
                for(cp in cps)
                {
                    var m=PLAY_URL_PATTERN.matcher(cp.html())
                    if(m.find())
                    {
                        //获得url
                        anima_url=m.group().replace("url: \"","")
                    }
                }


            }else{

                var ht4 = getHtml(fakeurl)
                var doc4 =Jsoup.parse(ht4)
                var cps= doc4.select("script")

                for(cp in cps )
                {
                    var m=PLAY_URL_PATTERN.matcher(cp.html())
                    if(m.find())
                    {
                        //获得url
                        anima_url=m.group().replace("url: \"","")
                    }

                }

            }
            Videolist.add(VideoUrl(anima_name,anima_url))

        }.join()


        }


       ca.onSuccess(Videolist)


    }









}