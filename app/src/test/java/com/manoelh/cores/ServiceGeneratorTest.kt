package com.manoelh.cores

import com.google.gson.Gson
import com.manoelh.cores.constants.WebServiceConstants
import com.manoelh.cores.service.RetrofitService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceGeneratorTest {

    val mockWebServer = MockWebServer()


    private fun createService(mockWebServer: MockWebServer): RetrofitService {
        mockWebServer.start()

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
        httpClient.addInterceptor(loggingInterceptor)

        val retrofitService = Retrofit.Builder()
            .baseUrl(mockWebServer.url(WebServiceConstants.URL))
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(httpClient.build())
            .build()
            .create(RetrofitService::class.java)
        return retrofitService
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `test if retrofitService interface is built before to be used to api connection`() {
       val service: RetrofitService = createService(mockWebServer)
       Assert.assertNotEquals(service, null)
    }
}