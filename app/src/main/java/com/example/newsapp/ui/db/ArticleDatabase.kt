package com.example.newsapp.ui.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.ui.Article

@Database(
    entities = [Article :: class],
    version  = 1
)

abstract class ArticleDatabase : RoomDatabase(){

    abstract fun getArticlesDao() : ArticleDao

    companion object{

    }
}