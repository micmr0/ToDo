package com.micmr0.todo

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestUtil private constructor() {
    val retrofit: Retrofit

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val builder = Retrofit.Builder()
            .baseUrl("http://example.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

        retrofit = builder.client(httpClient).build()
    }

    companion object {
        private var self: RestUtil? = null

        val instance: RestUtil
            get() {
                if (self == null) {
                    synchronized(RestUtil::class.java) {
                        if (self == null) {
                            self =
                                    RestUtil()
                        }
                    }
                }
                return self!!
            }
    }
}