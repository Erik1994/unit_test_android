package com.example.androidtesting.di

import android.content.Context
import androidx.room.Room
import com.example.androidtesting.Constants.BASE_URL
import com.example.androidtesting.Constants.DATABASE_NAME
import com.example.androidtesting.data.local.ShoppingItemDatabse
import com.example.androidtesting.data.remote.PixabayApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ShoppingItemDatabse::class.java, DATABASE_NAME).build()


    @Singleton
    @Provides
    fun privideSHoppingDao(
        databse: ShoppingItemDatabse
    ) = databse.shoppingDao()

    @Singleton
    @Provides
    fun providePixabayApi(): PixabayApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayApi::class.java)

    }
}