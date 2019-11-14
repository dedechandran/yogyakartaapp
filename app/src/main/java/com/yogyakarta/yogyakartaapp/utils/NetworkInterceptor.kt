package com.yogyakarta.yogyakartaapp.utils

import com.yogyakarta.yogyakartaapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val movieApiKey = BuildConfig.MOVIE_API_KEY
        val url = chain.request().url.newBuilder()
            .addQueryParameter("api_key", movieApiKey).build()
        val request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }
}