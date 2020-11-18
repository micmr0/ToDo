package com.micmr0.todo.factory

import com.micmr0.todo.ApiService
import com.micmr0.todo.RestUtil
import com.micmr0.todo.TodoRepository

object RepositoryFactory {
    fun createRMRepository() : TodoRepository {
        val api = RestUtil.instance.retrofit.create(ApiService::class.java)
        return TodoRepository(api)
    }
}