package com.karim.instagramapp.data.models


class Data {
    var id: String? = null

    var user: User? = null

    var from: From? = null

    var text: String? = null

    var images: Images? = null

    var created_time: String? = null

    var caption: Caption? = null

    var user_has_liked: Boolean = false

    var likes: Likes? = null

    var tags: List<String>? = null

    var filter: String? = null

    var comments: Comments? = null

    var type: String? = null

    var link: String? = null

    var location: Location? = null

    var attribution: String? = null

    var users_in_photo: List<String>? = null
}
