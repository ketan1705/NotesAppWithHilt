package com.ken.notesappwithhilt.di

import com.ken.notesappwithhilt.api.NotesApi
import com.ken.notesappwithhilt.api.UserApi
import com.ken.notesappwithhilt.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Set to BODY for full logging
        }
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(
//        authInterceptor: AuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient.Builder {
        return OkHttpClient.Builder()
//            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
    }

    @Singleton
    @Provides
    fun providerUserAPI(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient.Builder,
    ): UserApi {
        return retrofitBuilder.client(
            okHttpClient.build()
        ).build()
            .create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun provideNoteAPI(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient.Builder,
        authInterceptor: AuthInterceptor,
    ): NotesApi {
        return retrofitBuilder.client(
            okHttpClient.addInterceptor(authInterceptor).build()
        ).build().create(NotesApi::class.java)
    }
}