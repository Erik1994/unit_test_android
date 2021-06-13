package com.example.androidtesting.repository

import androidx.lifecycle.LiveData
import com.example.androidtesting.data.local.ShoppingItem
import com.example.androidtesting.data.remote.resource.Resource
import com.example.androidtesting.data.remote.responses.ImageResponse

interface ShoppingRepository {
    suspend fun insertShoppingItem(shoppingITem: ShoppingItem)
    suspend fun deleteDhoppingItem(shoppingITem: ShoppingItem)
    fun observeAllShoppingItems(): LiveData<List<ShoppingItem>>
    fun observeTotalPrice(): LiveData<Float>
    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}