package com.example.androidtesting.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidtesting.data.local.ShoppingItem
import com.example.androidtesting.data.remote.resource.Resource
import com.example.androidtesting.data.remote.responses.ImageResponse

class FakeShoppingRepository: ShoppingRepository {
    private val shoppingItems = mutableListOf<ShoppingItem>()
    private val observableShoppingItems = MutableLiveData<List<ShoppingItem>>(shoppingItems)
    private val observableTotalPrice = MutableLiveData<Float>()
    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData() {
        observableShoppingItems.postValue(shoppingItems)
        observableTotalPrice.postValue(getTotalPrice())
    }

    private fun getTotalPrice() : Float {
        return shoppingItems.sumByDouble { it.price.toDouble() }.toFloat()
    }
    override suspend fun insertShoppingItem(shoppingITem: ShoppingItem) {
        shoppingItems.add(shoppingITem)
        refreshLiveData()
    }

    override suspend fun deleteDhoppingItem(shoppingITem: ShoppingItem) {
        shoppingItems.remove(shoppingITem)
        refreshLiveData()
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return observableShoppingItems
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return observableTotalPrice
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return if(shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            return Resource.success(ImageResponse(listOf(), 0, 0))
        }
    }
}