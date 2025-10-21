package com.library.management.data

import kotlinx.coroutines.flow.Flow

class MemberRepository(private val dao: MemberDao) {
    val allMembers: Flow<List<Member>> = dao.getAllMembers()
    suspend fun insert(member: Member) = dao.insertMember(member)
    suspend fun update(member: Member) = dao.updateMember(member)
    suspend fun delete(member: Member) = dao.deleteMember(member)
}
