package com.capstone.tastematch.data.local.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.capstone.tastematch.data.local.database.dao.MenuDao
import com.capstone.tastematch.data.remote.api.ResponseItem

@Database(entities = [ResponseItem::class], version = 1)
abstract class TasteMatchDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao

    companion object {
        const val DATABASE_NAME = "menu_db"
    }
}