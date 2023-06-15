//package com.capstone.tastematch.data.local.database.database
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.capstone.tastematch.data.local.database.dao.MenuDao
//import com.capstone.tastematch.data.remote.api.ResponseItem
//
//@Database(entities = [ResponseItem::class], version = 1)
//abstract class TasteMatchDatabase : RoomDatabase() {
//    abstract fun menuDao(): MenuDao
//
//    companion object {
//        const val DATABASE_NAME = "menu_db"
//
//        @Volatile
//        private var INSTANCE :TasteMatchDatabase?=null
//
//        fun getInstance(context: Context): TasteMatchDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    TasteMatchDatabase::class.java,
//                    DATABASE_NAME
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//
//    }
//}