package com.example.newsaggregator.feature.bookmarks.data.local

import androidx.room.*
import com.example.newsaggregator.feature.bookmarks.data.local.model.BookmarkEntity
import com.example.newsaggregator.feature.bookmarks.di.BOOKMARKS_TABLE

@Dao
interface BookmarksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(entity: BookmarkEntity)

    @Query("SELECT * FROM $BOOKMARKS_TABLE")
    suspend fun read(): List<BookmarkEntity>

    @Update
    suspend fun update(entity: BookmarkEntity)

    @Delete
    suspend fun delete(entity: BookmarkEntity)

}