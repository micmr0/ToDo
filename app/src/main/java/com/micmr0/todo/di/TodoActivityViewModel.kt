package com.micmr0.todo.di

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.micmr0.todo.Todo
import com.micmr0.todo.TodoRepository
import com.micmr0.todo.ui.main.MainActivity
import com.uk.androidrecruitmentapp.StateLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TodoActivityViewModel(private val todoRepository: TodoRepository?) : ViewModel() {
    private val todoLiveData: StateLiveData<ArrayList<Todo>> = StateLiveData()


}