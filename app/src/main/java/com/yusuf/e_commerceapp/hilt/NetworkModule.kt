package com.yusuf.e_commerceapp.hilt

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yusuf.e_commerceapp.SouQApp
import com.yusuf.e_commerceapp.db.AppDatabase
import com.yusuf.e_commerceapp.db.ProductDao
import com.yusuf.e_commerceapp.hilt.service.auth.AuthService
import com.yusuf.e_commerceapp.hilt.service.ProductsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        return Retrofit.Builder()
            .baseUrl("http://shopappminiprojensas.000webhostapp.com/api/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
//        client.addInterceptor(
//            ChuckerInterceptor.Builder(SouQApp.context)
//                .collector(ChuckerCollector(SouQApp.context))
//                .maxContentLength(250000L)
//                .redactHeaders(emptySet())
//                .alwaysReadResponseBody(false)
//                .build()
//        )
        return client.build()

//        val duration = Duration.ofSeconds(30)
//        return OkHttpClient.Builder()
//            .connectTimeout(duration)
//            .readTimeout(duration)
//            .writeTimeout(duration)
//            .build()
    }
    @Provides
    @Singleton
    fun providesProductsService(retrofit: Retrofit) : ProductsService{
        return retrofit.create(ProductsService::class.java)
    }
    @Provides
    @Singleton
    fun providesAuthService(retrofit: Retrofit) : AuthService {
        return retrofit.create(AuthService::class.java)
    }
    @Provides
    @Singleton
    fun provideProductDao() : ProductDao{
        return AppDatabase.getDatabase(SouQApp.context).productDao()
    }
}