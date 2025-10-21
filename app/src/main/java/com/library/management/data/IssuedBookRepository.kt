package com.library.management.data

class IssuedBookRepository(private val dao: IssuedBookDao) {
    val issuedBooks = dao.getAllIssuedBooks()

    suspend fun addIssuedBook(book: IssuedBook) = dao.insertIssuedBook(book)
    suspend fun updateIssuedBook(book: IssuedBook) = dao.updateIssuedBook(book)
    suspend fun deleteIssuedBook(book: IssuedBook) = dao.deleteIssuedBook(book)
}
