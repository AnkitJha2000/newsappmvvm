package com.example.newsapp.ui.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.ui.adapter.NewsAdapter
import com.example.newsapp.ui.ui.MainActivity
import com.example.newsapp.ui.ui.NewsViewModel
import com.example.newsapp.ui.util.Resource
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {
    lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter : NewsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        // this is for delaying in searching news so that not too many requests are made that are unnecessary to make
        // so we add a delay of 500 ms so that it will start search after 500 ms after the last letter typed
        // job variable is used for these kind of operations

        var job : Job? = null
        etSearchNews.addTextChangedListener { editable->
            job?.cancel()
            job = MainScope().launch {
                delay(500)
                if(editable.toString().isNotEmpty())
                {
                    viewModel.searchNews(editable.toString())
                }
                Log.e("ERROR OCCURED IN SEARCH NEWS FRAGMENT !",
                        "ERROR OCCURED IN SEARCH NEWS FRAGMENT !"
                )
            }
        }

        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        newsAdapter.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("article" , it)

            }
            findNavController().navigate(
                    R.id.action_searchNewsFragment_to_articleFragment2,
                    bundle
            )
        }

        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
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
                        Log.e("ERROR OCCURED IN SEARCH NEWS FRAGMENT !",
                                "ERROR OCCURED IN SEARCH NEWS FRAGMENT ! : $message"
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
        progressSearchNews.visibility = View.INVISIBLE
    }
    private fun showProgressBar() {
        progressSearchNews.visibility = View.VISIBLE
    }
    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}