package com.micmr0.todo.di

import com.micmr0.todo.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(activity: MainActivity)
}