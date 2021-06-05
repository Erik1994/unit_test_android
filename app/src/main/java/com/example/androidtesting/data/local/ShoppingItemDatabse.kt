package com.example.androidtesting.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingItemDatabse: RoomDatabase() {
    abstract fun shoppingDao(): ShoppingDao
}