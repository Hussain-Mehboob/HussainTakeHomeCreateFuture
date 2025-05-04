package com.createfuture.takehome.data.remote.interceptor

import com.createfuture.takehome.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader("Authorization", BuildConfig.AUTH_TOKEN)
        return chain.proceed(request.build())
    }
}