package com.example.simplynewapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NewContentActivity : AppCompatActivity() {
    companion object {
        fun actionStart(context: Context, title: String, content: String) {
            val intent = Intent(context, NewContentActivity::class.java).apply {
                putExtra("news_title", title)
                putExtra("news_content", content)
            }

            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_content)
        val title = intent.getStringExtra("news_title") // 获取传入的新闻标题
        val content = intent.getStringExtra("news_content") // 获取传入的新闻内容
        if (title != null && content != null) {
            val newsContentFragment = supportFragmentManager.findFragmentById(R.id.newsContentFrag) as NewsContentFragment
            newsContentFragment.refresh(title, content)
        }
    }
}