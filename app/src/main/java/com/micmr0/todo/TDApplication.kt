package com.micmr0.todo

import android.app.Application
import com.micmr0.todo.di.ApiComponent
import com.micmr0.todo.di.ApiModule
import com.micmr0.todo.di.DaggerApiComponent

class TDApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        apiComponent = DaggerApiComponent.builder()
            .apiModule(ApiModule())
            .build()
    }

    companion object {
        lateinit var apiComponent: ApiComponent
    }
}
