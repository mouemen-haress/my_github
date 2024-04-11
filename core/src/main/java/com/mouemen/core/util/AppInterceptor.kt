package com.moemen.core.util

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AppInterceptor() : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        // Add the token to the request header
        val request = chain.request()
        request?.let {
            Log.e(
                "apiTag",
                "request to - >  ${it.url}  \n\n request Body is : ${it.body}"
            )
        }


        val builder = request.newBuilder()
        builder.header("Accept", "*/*")
        builder.header("Accept-Language", "en-us")
        builder.header("Connection", "keep-alive")

        val newRequest = builder.build()

        var response = chain.proceed(newRequest)

        response?.let {
            Log.e(
                "apiTag",
                "response from - > ${request.url} \n \n response Body  is : ${
                    ApiFormater.formatApi(
                        it.peekBody(2048).string()
                    ) ?: run { "response is null" }
                }"
            )
        }
        return response
    }


}