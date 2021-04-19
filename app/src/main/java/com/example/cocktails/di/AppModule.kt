package com.example.cocktails.di

import android.content.Context
import com.bumptech.glide.Glide
import com.example.cocktails.api.CocktailService
import com.example.cocktails.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getHttpLogger() : OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient
    ) = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)


    @Provides
    @Singleton
    fun provideCocktailService(
        retrofit: Retrofit.Builder
    ) : CocktailService {
        val retrofitService = retrofit.baseUrl(AppConstants.BASE_URL).build()
        return retrofitService.create(CocktailService::class.java)
    }

}