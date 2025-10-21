package com.library.management.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [Book::class, Member::class, IssuedBook::class],
    version = 3,
    exportSchema = false
)
abstract class LibraryDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao
    abstract fun memberDao(): MemberDao
    abstract fun issuedBookDao(): IssuedBookDao

    companion object {
        @Volatile
        private var INSTANCE: LibraryDatabase? = null

        // ✅ Migration from version 2 → 3
        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `issued_books` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        `bookTitle` TEXT NOT NULL,
                        `memberName` TEXT NOT NULL,
                        `issueDate` TEXT NOT NULL,
                        `returnDate` TEXT NOT NULL
                    )
                    """.trimIndent()
                )
            }
        }

        fun getDatabase(context: Context): LibraryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LibraryDatabase::class.java,
                    "library_db"
                )
                    // ✅ Add migration
                    .addMigrations(MIGRATION_2_3)
                    // ✅ Also allow reset if version 1 DB found
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
