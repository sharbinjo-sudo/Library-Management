package com.library.management.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "issued_books")
data class IssuedBook(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val bookTitle: String,
    val memberName: String,
    val issueDate: String,
    val returnDate: String
)
