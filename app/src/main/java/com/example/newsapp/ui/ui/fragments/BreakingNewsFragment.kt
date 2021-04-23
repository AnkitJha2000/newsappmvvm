package com.example.newsapp.ui.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.ui.adapter.NewsAdapter
import com.example.newsapp.ui.models.Article
import com.example.newsapp.ui.ui.MainActivity
import com.example.newsapp.ui.ui.NewsViewModel
import com.example.newsapp.ui.util.Resource
import kotlinx.android.synthetic.main.fragment_breaking_news.*
import retrofit2.Response

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        setupRecyclerView()

        newsAdapter.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("article" , it)
            }
            findNavController().navigate(
                    R.id.action_breakingNewsFragment_to_articleFragment,
                    bundle
            )
        }

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message.let { message ->
                        Log.e("ERROR OCCURED IN BREAKING NEWS FRAGMENT !",
                                "ERROR OCCURED IN BREAKING NEWS FRAGMENT ! : $message"
                        )
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }


        })

    }

    private fun hideProgressBar() {
        progressBreakingNews.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progressBreakingNews.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }

}