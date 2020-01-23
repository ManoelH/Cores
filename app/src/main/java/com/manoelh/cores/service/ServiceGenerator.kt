package com.manoelh.cores.service

import com.google.gson.Gson
import com.manoelh.cores.constants.WebServiceConstantes
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ServiceGenerator{

    companion object {
        val API_BASE_URL = WebServiceConstantes.URL

        fun <S> createService(serviceClass: Class<S>?): S {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
            httpClient.addInterceptor(loggingInterceptor)

            val retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(httpClient.build())
                .build()
            return retrofit.create(serviceClass)
        }
    }
}