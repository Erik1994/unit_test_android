package com.example.androidtesting.repository

import androidx.lifecycle.LiveData
import com.example.androidtesting.data.local.ShoppingDao
import com.example.androidtesting.data.local.ShoppingItem
import com.example.androidtesting.data.remote.PixabayApi
import com.example.androidtesting.data.remote.resource.Resource
import com.example.androidtesting.data.remote.responses.ImageResponse
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixbayApi: PixabayApi
): ShoppingRepository {
    override suspend fun insertShoppingItem(shoppingITem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingITem)
    }

    override suspend fun deleteDhoppingItem(shoppingITem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingITem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixbayApi.searchForImage(imageQuery)
            if(response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occurd", null)
            } else {
                Resource.error("An unknown error occurd", null)
            }
        } catch (e: Exception) {
            Resource.error("Couldn't reach the server", null)
        }
    }
}