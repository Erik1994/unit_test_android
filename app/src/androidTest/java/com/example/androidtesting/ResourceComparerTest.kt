package com.example.androidtesting

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test


class ResourceComparerTest {
    private lateinit var  resourceComparer: ResourceComparer

    //call this every time before the start of test

    @Before
    fun setUp() {
        resourceComparer = ResourceComparer()
    }

    //call this every time after the end of test
    @After
    fun teardown() {
        
    }

    @Test
    fun stringResourceSameAsString_ReturnsTrue() {
        //get context for test cases
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(
            context,
            R.string.app_name,
            "AndroidTesting"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun stringResourceSameAsString_ReturnsFalse() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.isEqual(
            context,
            R.string.app_name,
            "Hello"
        )
        assertThat(result).isFalse()
    }
}