package com.createfuture.takehome.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader("Authorization", "Bearer 754t!si@glcE2qmOFEcN")
        return chain.proceed(request.build())
    }
}