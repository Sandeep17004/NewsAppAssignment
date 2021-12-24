package com.example.newsappassignment.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsappassignment.databinding.MainFragmentBinding
import com.example.newsappassignment.di.modules.main.ViewModelProvidersFactory
import com.example.newsappassignment.storage.entity.NewsArticleDb
import com.example.newsappassignment.ui.main.adapter.NewsArticlesAdapter
import com.example.newsappassignment.ui.main.viewmodels.NewsViewModel
import com.example.newsappassignment.utils.NetworkResource
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class MainFragment @Inject constructor() :
    DaggerFragment(){

    @Inject
    lateinit var providersFactory: ViewModelProvidersFactory

    @Inject
    lateinit var newsArticleAdapter: NewsArticlesAdapter
    private lateinit var newsViewModel: NewsViewModel
    lateinit var viewBinding: MainFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = MainFragmentBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsViewModel = ViewModelProvider(this, providersFactory).get(NewsViewModel::class.java)
        newsViewModel.loadNewsHeadlines()
        observeViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.rvNews.apply {
            adapter = newsArticleAdapter
            layoutManager = LinearLayoutManager(view.context)
        }
    }

    private fun observeViewModel() {
        newsViewModel.getNewsHeadlines().observe(this, { resObj ->
            when (resObj.status) {
                NetworkResource.Status.LOADING -> {
                    viewBinding.showLoading = true
                }
                NetworkResource.Status.ERROR -> {
                    viewBinding.showLoading = false
                    Toast.makeText(
                        requireContext(),
                        resObj.throwable.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                NetworkResource.Status.SUCCESS -> {
                    viewBinding.showLoading = false
                    if (resObj.data?.isNotEmpty() == true) {
                        viewBinding.noNewsFound = false
                        newsArticleAdapter.addDataToAdapter(resObj.data)
                    } else {
                        viewBinding.noNewsFound = true
                    }
                }
            }
        })
    }
}