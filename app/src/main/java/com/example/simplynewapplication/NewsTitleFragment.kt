package com.example.simplynewapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class NewsTitleFragment : Fragment() {
    private var isTwopane = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_title_frag, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isTwopane = activity?.findViewById<View>(R.id.newsContentLayout) != null
        val layoutManager = LinearLayoutManager(activity)
        val newsTitleRecyclerView = view?.findViewById<View>(R.id.newsTitleRecyclerView) as RecyclerView
        newsTitleRecyclerView.layoutManager = layoutManager
        val adapter = NewsAdapter(getNews())
        newsTitleRecyclerView.adapter = adapter
    }

    private fun getNews(): List<News> {

        val newsList=ArrayList<News>()
        for (i in 1..30) {
            val news = News("this is news title $i",getRandomLengthString(
                    "this is news content $i"))
            newsList.add(news)
        }
        return newsList
    }

    private fun getRandomLengthString(str: String): String {
        val n = (1..30).random()
        val builder = StringBuilder()
        for (i in 1..n) {
            builder.append(str)
        }
        return builder.toString()
    }


    inner class NewsAdapter(val newList: List<News>) :
        RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val newsTitle: TextView = view.findViewById(R.id.newsTitle)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
            val holder = ViewHolder(view)
            holder.itemView.setOnClickListener {
                val news = newList[holder.adapterPosition]
                if (isTwopane) {
                    //如果是双页模式，则刷新NewsContentFragment中的内容
                    val fragment =
                        activity?.supportFragmentManager?.findFragmentById(R.id.newsContentFrag) as NewsContentFragment
                    fragment.refresh(news.title, news.content)
                } else {
                    //如果是单页模式，则直接启动NewsContentActivity
                    NewContentActivity.actionStart(parent.context, news.title, news.content)
                }
            }
            return holder
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val news = newList[position]
            holder.newsTitle.text = news.title
        }

        override fun getItemCount(): Int {
            return newList.size
        }
    }
}