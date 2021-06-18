package com.vikas.optimumuse.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vikas.optimumuse.model.Product

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDAO
}