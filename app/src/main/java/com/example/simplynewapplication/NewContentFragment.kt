package com.example.simplynewapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class NewsContentFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var contentLayout: View
    private lateinit var newsTitle : TextView
    private  lateinit var newsContent  : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.news_content_frag, container, false)
    }

    fun refresh(title: String, content: String) {
        contentLayout= view?.findViewById(R.id.contentLayout) as View
        newsTitle=view?.findViewById(R.id.newsTitle) as TextView
        newsContent=view?.findViewById(R.id.newsContent) as TextView
        contentLayout.visibility = View.VISIBLE
        newsTitle.text = title
        newsContent.text = content

    }

}