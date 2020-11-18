package com.micmr0.todo.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.micmr0.todo.Todo
import com.micmr0.todo.TodoRepository
import com.uk.androidrecruitmentapp.StateLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainActivityViewModel(private val todoRepository: TodoRepository?) : ViewModel() {
    val todoLiveData: StateLiveData<ArrayList<Todo>> = StateLiveData()

    @SuppressLint("CheckResult")
    fun getToDos(activity: MainActivity) {
        todoRepository!!
            .getToDos(activity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                todoLiveData.postSuccess(it)
            }
    }

    @SuppressLint("CheckResult")
    fun editTodo(activity: MainActivity, todo : Todo) {
        todoRepository!!
            .editTodo(activity, todo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                todoLiveData.postSuccess(it)
            }
    }

    @SuppressLint("CheckResult")
    fun removeTodo(activity: MainActivity, todo : Todo) {
        todoRepository!!
            .removeTodo(activity, todo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                todoLiveData.postSuccess(it)
            }
    }
}