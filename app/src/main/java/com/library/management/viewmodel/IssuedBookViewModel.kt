package com.library.management.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.library.management.data.IssuedBook
import com.library.management.data.IssuedBookRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class IssuedBookViewModel(private val repo: IssuedBookRepository) : ViewModel() {

    val issuedBooks = repo.issuedBooks.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun addIssuedBook(book: IssuedBook) {
        viewModelScope.launch { repo.addIssuedBook(book) }
    }

    fun updateIssuedBook(book: IssuedBook) {
        viewModelScope.launch { repo.updateIssuedBook(book) }
    }

    fun deleteIssuedBook(book: IssuedBook) {
        viewModelScope.launch { repo.deleteIssuedBook(book) }
    }
}
