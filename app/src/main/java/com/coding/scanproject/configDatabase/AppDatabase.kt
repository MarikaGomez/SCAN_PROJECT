package com.coding.scanproject.configDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.coding.scanproject.dao.MealsDAO
import com.coding.scanproject.entity.MealsData
import kotlinx.coroutines.CoroutineScope

@Database(entities = [MealsData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mealsDAO(): MealsDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mealsdb"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}


