package com.manoelh.cores.service

import com.manoelh.cores.model.Cores
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST


interface RetrofitService {

    @Headers("X-Parse-Application-Id: XUGUteSRjfXSLvA4AKNAbFwjdF0CfYuInJesxmlF",
            "X-Parse-REST-API-Key: KUXVh7zrFLhs1uS849XUog6CizcNviEP9rdMWfg1",
            "Content-Type: application/json")
    @FormUrlEncoded
    @POST("colors")
    fun converterUnidade(@Field("result") result: MutableList<String>?): Call<Cores?>?
}