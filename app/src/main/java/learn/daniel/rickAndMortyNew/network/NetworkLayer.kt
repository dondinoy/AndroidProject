package learn.daniel.rickAndMortyNew.network

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import learn.daniel.rickAndMortyNew.RickAndMortyApplication
import learn.daniel.rickAndMortyNew.RickAndMortyApplication.Companion.context
import learn.daniel.rickAndMortyNew.network.service.ApiClient
import learn.daniel.rickAndMortyNew.network.service.RickAndMortyService
import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkLayer {
    val retrofit: Retrofit = Retrofit.Builder()
        .client(getLoggingHttpClient())
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val rickAndMortyService: RickAndMortyService by lazy {
        retrofit.create(RickAndMortyService::class.java)
    }
    val apiClient= ApiClient(rickAndMortyService)

    private fun getLoggingHttpClient():OkHttpClient{
        val builder= OkHttpClient.Builder()
        builder.addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        builder.addInterceptor(
            ChuckerInterceptor.Builder(RickAndMortyApplication.context)
                .collector(ChuckerCollector(context))
                .maxContentLength(250000L)
                .redactHeaders(emptyList())
                .alwaysReadResponseBody(false)
                .build()
        )
        return builder.build()
        }
    }
