package com.example.androidtesting.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtesting.data.local.ShoppingItem
import com.example.androidtesting.data.remote.resource.Event
import com.example.androidtesting.data.remote.resource.Resource
import com.example.androidtesting.data.remote.responses.ImageResponse
import com.example.androidtesting.repository.ShoppingRepository
import kotlinx.coroutines.launch

class ShoppingViewModel @ViewModelInject constructor(
    private val repository: ShoppingRepository
) : ViewModel() {
    val shoppingItems = repository.observeAllShoppingItems()
    val totalPrice = repository.observeTotalPrice()

    private val _images = MutableLiveData<Event<Resource<ImageResponse>>>()
    val images: LiveData<Event<Resource<ImageResponse>>> = _images

    private val _curImageUrl = MutableLiveData<String>()
    val curImageUrl: LiveData<String> = _curImageUrl

    private val _insertShoppingItemStatus = MutableLiveData<Event<Resource<ShoppingItem>>>()
    val insertShoppingItemStatus: LiveData<Event<Resource<ShoppingItem>>> = _insertShoppingItemStatus

    fun setCurrentImageUrl(url: String) {
        _curImageUrl.postValue(url)
    }

    fun deleteShoppingItem(item: ShoppingItem) = viewModelScope.launch {
        repository.deleteDhoppingItem(item)
    }

    fun insertShoppingItem(item: ShoppingItem) = viewModelScope.launch {
        repository.insertShoppingItem(item)
    }

    fun insertShoppingItem(name: String, amountString: String, priceString: String) {

    }

    fun searchForImage(imageQuery: String) {

    }
}