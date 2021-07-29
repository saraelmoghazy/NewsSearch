package com.example.newssearch.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newssearch.R
import com.example.newssearch.viewmodel.NewsViewModel
import com.example.newssearch.viewmodel.NewsViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_details_fragment.view.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class NewsDetailsFragment : Fragment(), KodeinAware {

    override val kodein: Kodein by kodein()
    private val viewModelFactory: NewsViewModelFactory by instance()

    private val viewModel: NewsViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            viewModelFactory
        ).get(NewsViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.news_details_fragment, container, false)

        viewModel.selectedArticle.observe(requireActivity(), Observer {
            val article = it
            rootView.txtArticleTitle.text = article.title
            rootView.txtDate.text = article.publishedAt
            rootView.txtAuthor.text = article.author
            rootView.txtContent.text = article.content
            Picasso.get()
                .load(article.urlToImage)
                .placeholder(R.drawable.ic_cover_place_holder)
                .error(R.drawable.ic_cover_place_holder)
                .resize(2048, 1600)
                .onlyScaleDown()
                .into(rootView.icCover)

            rootView.btnMore.setOnClickListener {
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(article.url)
                )
            }
        })

        return rootView
    }
}
