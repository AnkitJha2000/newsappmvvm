package com.example.newsapp.ui.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.newsapp.R
import com.example.newsapp.ui.ui.MainActivity

class SearchNewsFragment : Fragment(R.layout.fragment_search_news) {
    lateinit var viewModel: ViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

    }
}