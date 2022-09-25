package com.example.newsaggregator

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsaggregator.feature.bookmarks.data.local.BookmarksDao
import com.example.newsaggregator.feature.bookmarks.data.local.model.BookmarkEntity

@Database(entities = [BookmarkEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun bookmarksDao(): BookmarksDao
}