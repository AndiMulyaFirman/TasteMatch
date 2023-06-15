//package com.capstone.tastematch.data.local.database.Repository
//
//import androidx.lifecycle.LiveData
//import com.capstone.tastematch.data.local.database.dao.MenuDao
//import com.capstone.tastematch.data.remote.api.ApiService
//import com.capstone.tastematch.data.remote.api.ResponseItem
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//
//class MenuRepository {
//    class MenuRepository(
//        private val apiService: ApiService,
//        private val menuDao: MenuDao
//        ) {
//
//        suspend fun fetchMenu() {
//            withContext(Dispatchers.IO) {
//                try {
//                    val response = apiService.getMenu().execute().body()
//                    response?.response?.let { menuList ->
//                        val nonNullMenuList = menuList.filterNotNull()
//                        menuDao.insertResponseItems(nonNullMenuList)
//                    }
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
//        }
//
//        suspend fun getAllMenuItems(): LiveData<List<ResponseItem>> {
//            return menuDao.getAllResponseItems()
//        }
//
//    }
//}