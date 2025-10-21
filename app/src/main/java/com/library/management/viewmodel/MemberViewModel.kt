package com.library.management.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.library.management.data.*
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MemberViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = LibraryDatabase.getDatabase(app).memberDao()
    private val repo = MemberRepository(dao)
    val members = repo.allMembers.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addMember(member: Member) = viewModelScope.launch { repo.insert(member) }
    fun updateMember(member: Member) = viewModelScope.launch { repo.update(member) }
    fun deleteMember(member: Member) = viewModelScope.launch { repo.delete(member) }
}