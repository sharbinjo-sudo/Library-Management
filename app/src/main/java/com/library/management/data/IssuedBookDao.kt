package com.library.management.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface IssuedBookDao {

    @Query("SELECT * FROM issued_books ORDER BY id DESC")
    fun getAllIssuedBooks(): Flow<List<IssuedBook>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIssuedBook(book: IssuedBook)

    @Update
    suspend fun updateIssuedBook(book: IssuedBook)

    @Delete
    suspend fun deleteIssuedBook(book: IssuedBook)
}
