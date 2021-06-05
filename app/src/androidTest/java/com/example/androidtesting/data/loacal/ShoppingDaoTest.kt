package com.example.androidtesting.data.loacal

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.androidtesting.data.local.ShoppingDao
import com.example.androidtesting.data.local.ShoppingItem
import com.example.androidtesting.data.local.ShoppingItemDatabse
import com.example.androidtesting.getOrAwaitValue
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ShoppingDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var database: ShoppingItemDatabse
    private lateinit var dao: ShoppingDao

    @Before
    fun setup() {
        //instead of databseBuilder() we use this
        //this saves in ram instead of storage
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingItemDatabse::class.java
        //we need to have an access to database in main thread
        // to save indipendency
        ).allowMainThreadQueries().build()
        dao = database.shoppingDao()
    }


    @Test
    fun insertShoppingItem() = runBlockingTest {
        val shopingItem = ShoppingItem(
            "name",
            1,
            1f,
            "url",
            id = 1
        )
        dao.insertShoppingItem(shopingItem)
        val allShoppingItems = dao.observeAllShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItems).contains(shopingItem)
    }

    @Test
    fun deleteShoppingItem() = runBlockingTest {
        val shopingItem = ShoppingItem(
            "name",
            1,
            1f,
            "url",
            id = 1
        )
        dao.insertShoppingItem(shopingItem)
        dao.deleteShoppingItem(shopingItem)

        val allShoppingItems = dao.observeAllShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItems).doesNotContain(shopingItem)
    }

    @Test
    fun observeTotalPriceSum() = runBlockingTest {
        val shopingItem1 = ShoppingItem(
            "name",
            0,
            100f,
            "url",
            id = 1
        )
        val shopingItem2 = ShoppingItem(
            "name",
            2,
            10f,
            "url",
            id = 2
        )
        val shopingItem3 = ShoppingItem(
            "name",
            4,
            5.5f,
            "url",
            id = 3
        )
        dao.insertShoppingItem(shopingItem1)
        dao.insertShoppingItem(shopingItem2)
        dao.insertShoppingItem(shopingItem3)

        val totalPrice = dao.observeTotalPrice().getOrAwaitValue()

        assertThat(totalPrice).isEqualTo(2 * 10f + 4 * 5.5f)
    }

    @After
    fun tearDown() {
        database.close()
    }
}