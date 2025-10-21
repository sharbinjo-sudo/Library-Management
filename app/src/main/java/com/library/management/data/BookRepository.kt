package com.library.management.data

import kotlinx.coroutines.flow.Flow

class BookRepository(private val dao: BookDao) {
    val allBooks: Flow<List<Book>> = dao.getAllBooks()
    suspend fun insert(book: Book) = dao.insertBook(book)
    suspend fun update(book: Book) = dao.updateBook(book)
    suspend fun delete(book: Book) = dao.deleteBook(book)
}
