package com.library.management.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.library.management.data.*
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BookViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = LibraryDatabase.getDatabase(app).bookDao()
    private val repo = BookRepository(dao)
    val books = repo.allBooks.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addBook(book: Book) = viewModelScope.launch { repo.insert(book) }
    fun updateBook(book: Book) = viewModelScope.launch { repo.update(book) }
    fun deleteBook(book: Book) = viewModelScope.launch { repo.delete(book) }
}
