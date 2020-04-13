package com.karim.instagramapp.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.karim.instagramapp.R
import com.karim.instagramapp.data.models.Data
import java.util.*

class CommentListRecyclerAdapter(val context: Context,
                                 var comments: List<Data>):
    RecyclerView.Adapter<CommentListRecyclerAdapter.ViewHolder>()

{

    override fun getItemCount() = comments.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.comment_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = comments[position]
        with(holder) {

            dateText?.let {

                val date = Date(comment.created_time!!.toLong() * 1000)

                it.text = date.toLocaleString()

            }

            usernameText.text = comment.from?.username

            commentText.text = comment.text

        }
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val dateText = itemView.findViewById<TextView>(R.id.dateTv)

        val commentText = itemView.findViewById<TextView>(R.id.commentTv)

        val usernameText = itemView.findViewById<TextView>(R.id.usernameTv)
    }

}