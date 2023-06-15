//package com.capstone.tastematch.data.local.database.dao
//
//import androidx.lifecycle.LiveData
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.capstone.tastematch.data.remote.api.ResponseItem
//
//@Dao
//interface MenuDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertResponseItems(items: List<ResponseItem>)
//
//    @Query("SELECT * FROM menudb")
//    suspend fun getAllResponseItems(): LiveData<List<ResponseItem>>
//
//    @Query("SELECT * FROM menudb WHERE namaMakanan LIKE '%' || :query || '%'")
//    suspend fun searchItems(query: String): List<ResponseItem>
//}