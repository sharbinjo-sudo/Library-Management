package com.library.management.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.library.management.data.IssuedBookRepository
import com.library.management.data.LibraryDatabase

class IssuedBookViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IssuedBookViewModel::class.java)) {
            val dao = LibraryDatabase.getDatabase(context).issuedBookDao()
            val repo = IssuedBookRepository(dao)
            @Suppress("UNCHECKED_CAST")
            return IssuedBookViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
