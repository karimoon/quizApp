package com.karim.instagramapp.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.karim.instagramapp.R
import com.karim.instagramapp.data.models.Data
import java.util.*

class PostListRecyclerAdapter(val context: Context,
                              var posts: List<Data>, val itemListener: ViewCommentsClickItemListener):
    RecyclerView.Adapter<PostListRecyclerAdapter.ViewHolder>()

{

    override fun getItemCount() = posts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.post_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]
        with(holder) {

            dateText?.let {

                val date = Date(post.created_time!!.toLong() * 1000)

                it.text = date.toLocaleString()

            }

            Glide.with(context)
                .load(post.images?.thumbnail?.url)
                .into(postImage)

            if (post.user_has_liked){
                likeText.visibility = View.VISIBLE
                likeText.text = ""+ post.likes!!.count + " " + if (post.likes!!.count > 1) "Likes" else "Like"
            }

            post.caption?.let {
                captionText.visibility = View.VISIBLE
                captionText.text = it.text
            }

            post.location?.let {
                it.name?.let {
                    locationNameText.visibility= View.VISIBLE
                    locationNameText.text = ", "+ it
                }
            }

            if (post.comments!!.count > 0){
                commentText.visibility = View.VISIBLE
                commentText.text =  if (post.comments!!.count> 1) "View all " + post.comments!!.count + " comments"  else "View comment"


                post.comments!!.data?.let {

                    commentRecyclerview.visibility= View.VISIBLE
                    progressBar.visibility= View.GONE
                    val commentListRecyclerAdapter = CommentListRecyclerAdapter(context, it)
                    commentRecyclerview.adapter = commentListRecyclerAdapter

                    commentText.text =  if (post.comments!!.count> 1) "Hide comments"  else "Hide comment"

                }

            }


            commentText.setOnClickListener{

                if (post.comments!!.data == null){
                    //show comments
                    progressBar.visibility= View.VISIBLE
                    itemListener.onViewCommentsClick(post , position)

                }
                else{
                    //hide comments
                    commentText.text =  if (post.comments!!.count> 1) "View all " + post.comments!!.count + " comments"  else "View comment"
                    post.comments!!.data = null
                    commentRecyclerview.visibility= View.GONE
                }

            }
        }
    }

    fun notifyItemWithComments(
        data: List<Data>?,
        position: Int
    ) {

        posts[position].comments?.data = data

        notifyDataSetChanged()

    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val dateText = itemView.findViewById<TextView>(R.id.dateTv)
        val locationNameText = itemView.findViewById<TextView>(R.id.locationNameTv)
        val likeText = itemView.findViewById<TextView>(R.id.likeTv)
        val captionText = itemView.findViewById<TextView>(R.id.captionTv)
        val commentText = itemView.findViewById<TextView>(R.id.commentTv)
        val postImage = itemView.findViewById<ImageView>(R.id.postImage)
        val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)
        val commentRecyclerview = itemView.findViewById<RecyclerView>(R.id.commentsRecyclerView)
    }

    interface ViewCommentsClickItemListener {
        fun onViewCommentsClick(post: Data, position: Int)
    }

}