package com.karim.instagramapp.data.retrofit

import com.karim.instagramapp.data.models.CommentsEntry
import com.karim.instagramapp.data.models.PostEntry
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface InstagramRetrofitService {

    @GET("recent")
    suspend fun getPhotosEntry(@Query("access_token") accessToken : String) : Response<PostEntry>

    @GET("comments")
    suspend fun getComments(@Query("access_token") accessToken : String) : Response<CommentsEntry>


}