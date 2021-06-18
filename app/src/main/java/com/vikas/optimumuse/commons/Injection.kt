package com.vikas.optimumuse.commons

import android.content.Context
import androidx.room.Room
import com.vikas.optimumuse.model.db.AppDatabase
import java.lang.ref.WeakReference


object Injection {
    lateinit var context: WeakReference<Context>

    fun getRoomDatabase(): AppDatabase {
        return Room.databaseBuilder(
            context.get()!!,
            AppDatabase::class.java, "product-list"
        ).allowMainThreadQueries().build()
    }
}