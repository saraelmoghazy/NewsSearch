package com.example.newssearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newssearch.R
import com.example.newssearch.databinding.PartialNewsBinding
import com.example.newssearch.model.ArticlesItem
import com.example.newssearch.viewmodel.NewsViewModel
import com.example.newssearch.viewmodel.NewsViewModelFactory
import kotlinx.android.synthetic.main.news_fragment.view.*
import kotlinx.coroutines.flow.collect
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class NewsFragment : Fragment(), KodeinAware, NewsAdapter.ArticleClickListener {
    lateinit var rootView: View
    override val kodein: Kodein by kodein()
    private val viewModelFactory: NewsViewModelFactory by instance()

    private val viewModel: NewsViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            viewModelFactory
        ).get(NewsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getNewsViewModel()
    }

    fun getNewsViewModel(): NewsViewModel {
        return viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.news_fragment, container, false)
        initNewsPagingAdapter()
        return rootView
    }

    private fun initNewsPagingAdapter() {
        val newsAdapter = NewsAdapter(this)
        rootView.rvNews.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.articles.collect { pagingData ->
                newsAdapter.submitData(pagingData)
            }
        }
    }


    override fun onArticleClicked(article: ArticlesItem) {
        viewModel.selectedArticle.value = article
        (activity as MainActivity).navHostFragment.navController.navigate(R.id.newsToNewsDetails)
    }
}