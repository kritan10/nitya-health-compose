package com.kritan.nityahealth.base.di

import com.kritan.nityahealth.auth.AppAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://health.sajiloweb.com/api/"

    @Singleton
    @Provides
    fun provideRetrofitService(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun providesAuthClient(appAuth: AppAuth): OkHttpClient {
        val authToken = appAuth.authState.value.token ?: ""
        val interceptor = Interceptor { chain ->
            val request = chain.request()
            val authRequest = request.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer $authToken")
                .build()
            chain.proceed(authRequest)
        }
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideKtorClient(): HttpClient {
        val interceptor = Interceptor { chain ->
            val request = chain.request()
            val authRequest = request.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer ktor")
                .build()
            chain.proceed(authRequest)
        }

        val client = HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })

            }
            engine {
                config {
                    followRedirects(true)
                }
                addInterceptor(interceptor)
                addNetworkInterceptor(interceptor)
            }
        }

        return client
    }
}