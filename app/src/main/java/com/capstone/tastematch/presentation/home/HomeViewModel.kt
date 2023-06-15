package com.capstone.tastematch.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.tastematch.data.local.database.Repository.MenuRepository
import com.capstone.tastematch.data.remote.api.ResponseItem
import kotlinx.coroutines.launch

class HomeViewModel(private val menuRepository: MenuRepository.MenuRepository) : ViewModel() {

    private val _menuItems = MutableLiveData<List<ResponseItem>>()
    val menuItems: LiveData<List<ResponseItem>> get() = _menuItems

    fun fetchMenu() {
        viewModelScope.launch {
            menuRepository.fetchMenu()
        }
    }

    fun getAllMenuItems() {
        viewModelScope.launch {
            menuRepository.getAllMenuItems().observeForever { items ->
                _menuItems.value = items
            }
        }
    }
}
