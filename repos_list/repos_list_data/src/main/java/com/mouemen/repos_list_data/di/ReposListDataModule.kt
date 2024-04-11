package com.example.tracker_data.di

import android.app.Application
import com.example.tracker_data.remote.ReposApi
import com.moemen.core.util.AppInterceptor
import com.mouemen.core.Constants
import com.mouemen.repos_list_data.repository.RepsoRepositoryImpl
import com.mouemen.repos_list_domain.reposiory.RepsoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReposListDataModule {

    @Provides
    @Singleton
    fun provideAppInterceptor(): AppInterceptor {
        return AppInterceptor()
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(appInterceptor: AppInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).addInterceptor(appInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideReposApi(client: OkHttpClient): ReposApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideReposRepository(api: ReposApi): RepsoRepository = RepsoRepositoryImpl(api)
}

