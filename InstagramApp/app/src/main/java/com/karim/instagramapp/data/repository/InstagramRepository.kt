package com.karim.instagramapp.data.repository

import com.karim.instagramapp.ACCESS_TOKEN
import com.karim.instagramapp.API_COMMENT_URL
import com.karim.instagramapp.API_URL
import com.karim.instagramapp.data.models.CommentsEntry
import com.karim.instagramapp.data.retrofit.InstagramRetrofitService
import com.karim.instagramapp.data.models.PostEntry
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory



class InstagramRepository {

    suspend fun getPhotos() : PostEntry? {

        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create(InstagramRetrofitService::class.java)

        return service.getPhotosEntry(ACCESS_TOKEN).body()
    }

    suspend fun getComments(mediaId : String) : CommentsEntry? {

        val retrofit = Retrofit.Builder()
            .baseUrl(API_COMMENT_URL+mediaId+"/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()


        val service = retrofit.create(InstagramRetrofitService::class.java)

        return service.getComments(ACCESS_TOKEN).body()

    }



}