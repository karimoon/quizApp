package com.karim.instagramapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karim.instagramapp.data.models.CommentsEntry
import com.karim.instagramapp.data.models.PostEntry
import com.karim.instagramapp.data.repository.InstagramRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val dataRepo = InstagramRepository()

    val photosData = MutableLiveData<PostEntry>()

    val CommentsData = MutableLiveData<CommentsEntry>()

    var lastClickedPosition :Int? = null

    init {
        getPhotos()
    }

    fun getPhotos(){
        CoroutineScope(Dispatchers.IO).launch {
            val serviceData = dataRepo.getPhotos()

            photosData.postValue(serviceData)
        }

    }

    fun getComments(mediaId : String){
        CoroutineScope(Dispatchers.IO).launch {

            val serviceData = dataRepo.getComments(mediaId)

            CommentsData.postValue(serviceData)
        }
    }
}
