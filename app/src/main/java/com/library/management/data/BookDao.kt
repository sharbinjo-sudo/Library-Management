package com.library.management.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert suspend fun insertBook(book: Book)
    @Update suspend fun updateBook(book: Book)
    @Delete suspend fun deleteBook(book: Book)
    @Query("SELECT * FROM books ORDER BY title ASC")
    fun getAllBooks(): Flow<List<Book>>
}
