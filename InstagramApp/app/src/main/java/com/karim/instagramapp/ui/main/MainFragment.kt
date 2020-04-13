package com.karim.instagramapp.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.karim.instagramapp.R
import com.karim.instagramapp.data.models.Data

class MainFragment : Fragment() , PostListRecyclerAdapter.ViewCommentsClickItemListener {


    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var recyclerView: RecyclerView

    private lateinit var postListRecyclerAdapter : PostListRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        recyclerView = view.findViewById(R.id.recyclerview)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)


        viewModel.photosData.observe(viewLifecycleOwner , Observer {
            postListRecyclerAdapter = PostListRecyclerAdapter(requireContext(), it.data!! , this )
            recyclerView.adapter = postListRecyclerAdapter

        })

        viewModel.CommentsData.observe(viewLifecycleOwner , Observer {

            postListRecyclerAdapter.notifyItemWithComments(it.data, viewModel.lastClickedPosition!!)

        })
    }

    override fun onViewCommentsClick(post: Data, position: Int) {

        viewModel.getComments(post.id.toString())

        viewModel.lastClickedPosition = position
    }

}
