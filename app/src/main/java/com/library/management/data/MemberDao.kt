package com.library.management.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MemberDao {
    @Insert suspend fun insertMember(member: Member)
    @Update suspend fun updateMember(member: Member)
    @Delete suspend fun deleteMember(member: Member)
    @Query("SELECT * FROM members ORDER BY name ASC")
    fun getAllMembers(): Flow<List<Member>>
}
