package com.vikas.optimumuse.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "name") val title: String,
    @ColumnInfo(name = "expiry") val expiryPeriod: String,
    var isConsumed: Boolean = false
)