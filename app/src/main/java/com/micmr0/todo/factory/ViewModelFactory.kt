package com.micmr0.todo.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.micmr0.todo.ui.main.MainActivityViewModel
import com.micmr0.todo.TodoRepository

class ViewModelFactory(private val todoRepository: TodoRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(todoRepository) as T
    }

}