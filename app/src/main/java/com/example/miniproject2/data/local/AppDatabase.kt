package com.example.miniproject2.data.local

import android.content.Context
import androidx.room.*
import com.example.miniproject2.data.model.User

@androidx.room.Database(entities = [User::class], version = 1)
abstract class AppDatabase : androidx.room.RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}