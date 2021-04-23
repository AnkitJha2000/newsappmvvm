package com.example.newsapp.ui.ui.fragments

import android.app.Fragment
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.newsapp.R
import com.example.newsapp.ui.models.Article
import com.example.newsapp.ui.ui.MainActivity
import com.example.newsapp.ui.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : androidx.fragment.app.Fragment(R.layout.fragment_article) {

    lateinit var viewModel: NewsViewModel
    val args : ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        val article = args.article

        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }

        fabSaveNews.setOnClickListener{
            viewModel.saveArticle(article)
            Snackbar.make(view , "Article saved Successfully !!" , Snackbar.LENGTH_SHORT).show()
        }
    }
}