package com.vikas.optimumuse.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vikas.optimumuse.model.Product

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE != null) {
                return INSTANCE as AppDatabase
            }
            synchronized(this) {
                if (INSTANCE == null) {
                    val db = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "productDb"
                    ).build()
                    INSTANCE = db
                }
                return INSTANCE as AppDatabase
            }
        }
    }
}