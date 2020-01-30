package com.manoelh.cores.service

import android.util.Log
import com.manoelh.cores.model.Colors
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ServiceRequest {

    private val TAG = "ServiceRequest"

    fun searchColorFromAPI(callback:(Colors?)-> Unit) {
        val service: RetrofitService = ServiceGenerator.createService(RetrofitService::class.java)
        val result: MutableList<String> = arrayListOf()
        val call: Call<Colors?>? = service.unityConverter(result)
        call?.enqueue(object : Callback<Colors?> {
            override fun onResponse(call: Call<Colors?>?, response: Response<Colors?>) {
                if (response.isSuccessful) {
                    val serviceResponse: Colors? = response.body()
                        callback(serviceResponse)
                }

                else {
                    val errorBody: ResponseBody = response.errorBody()
                    Log.e(TAG, errorBody.toString())
                    callback(null)
                }
            }

            override fun onFailure(call: Call<Colors?>?, t: Throwable?) {
                Log.e(TAG, t?.message)
                callback(null)
            }
        })
    }

}