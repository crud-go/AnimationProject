package com.test.animation.logic.model.picmodel.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.test.animation.logic.network.request.sortway
import java.io.Serializable


class SignInResponse (
    val token:String
    )
data class GeneralResponse<Ts>(
    val code:Int,
    val message:String,
    val data:Ts,
)

data class ThumbnailObject(
    val originalName:String,
    val fileServer :String,
    val path:String,
)

data class ComicListObj(
    val thumb: ThumbnailObject,
    val author: String,
    val finished:Boolean,
    val categories: ArrayList<String>,
    val title: String,
    @SerializedName("_id")
    val comicId: String,
    val likesCount:Int,
)
data class ComiclistData(


    val total:Int,
    val page:Int,
    val pages:Int,
    val docs:ArrayList<ComicListObj>,
    val limit:Int,

)
data class ComicListResponse(
    val comics:ComiclistData
)
data class SearchObj(
    var token: String,
    var page:Int,
    var Sort:sortway
)
data class KeywordsResponse(
    var keywords:ArrayList<String>
)
data class WAKAresponse(
    val adKeyword:String,
 val addresses:ArrayList<String>,
 val message:String,
 val status:String,
 val  waka:String


)
data class InitialResponse(
    val isIdUpdated:Boolean,
    val isPunched:Boolean


)



data class CreatorObject(
    var avatar: ThumbnailObject,
    @SerializedName("_id")
    var creatorId: String,
    var gender: String,
    var name: String,
)

data class ComicDetailObject(
    var allowComment:Boolean,
    var allowDownload:Boolean,
    var author: String,
    var categories: ArrayList<String>,
    var chineseTeam: String,
    @SerializedName("_id")
    var comicId: String,
    var commentsCount :Int,
    @SerializedName("created_at")
    var createdAt: String,
    @SerializedName("_creator")
    var creator: CreatorObject,
    var description: String,
    @SerializedName("epsCount")
    var episodeCount:Int,
    var finished:Boolean,
    var isFavourite:Boolean,
    var isLiked:Boolean,
    var likesCount:Int,
    var pagesCount:Int,
    var tags: ArrayList<String>,
    var thumb: ThumbnailObject,
    var title: String,
    @SerializedName("updated_at")
    var updatedAt: String,
    var viewsCount:Int
)
data class ComicDetailResponse (
    var comic:ComicDetailObject
        )





data class ComicEpisodeObject(
    @SerializedName("_id")
    var episodeId: String,
    var order :Int,
    var selected :Boolean,
    var status :Int,
    var title: String,
    @SerializedName("updated_at")
    var updatedAt: String


)


data class ComicEpisodeData(
    var docs: ArrayList<ComicEpisodeObject>,
    var limit:Int,
    var page:Int,
    var pages:Int,
    var total:Int

)
data class ComicEpisodeResponse(
    var eps:ComicEpisodeData

)
data class ComicPagesResponse(
    var ep:ComicEpisodeObject,
     var pages:ComicPageData

)
data class ComicPageData(
    val docs:ArrayList<ComicPageObject>,
    val limit:Int,
    val  page :Int,
    val pages:Int,
    val total:Int
            )
data class ComicPageObject(
    @SerializedName("_id")
    var comicPageId:String,
    val media:ThumbnailObject
)



data class LeaderboardComicListObject(
    var author: String,
    var categories: ArrayList<String>,
    @SerializedName("_id")
    var comicId: String,
    @SerializedName("epsCount")
    var episodeCount:Int,
    var finished :Boolean,
    var leaderboardCount:Int,
    var likesCount:Int,
    var pagesCount :Int,
    var thumb: ThumbnailObject,
    var title: String,
    var viewsCount:Int

)
data class LeaderboardResponse(
    val comics:ArrayList<LeaderboardComicListObject>


)