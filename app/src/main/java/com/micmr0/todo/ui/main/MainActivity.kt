package com.micmr0.todo.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.micmr0.todo.*
import com.micmr0.todo.factory.RepositoryFactory
import com.micmr0.todo.factory.ViewModelFactory
import io.reactivex.annotations.NonNull
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TDApplication.apiComponent.inject(this)

        mainActivityViewModel =
            ViewModelProviders.of(this, ViewModelFactory(RepositoryFactory.createRMRepository()))
                .get(MainActivityViewModel::class.java)
        mainActivityViewModel.todoLiveData.observe(this, this::handleToDos)
        mainActivityViewModel.getToDos(this)

        title = getString(R.string.todos_list)
    }

    private fun handleToDos(@NonNull toDos: StateData<ArrayList<Todo>>?) {
        progress_bar.visibility = View.GONE
        replaceFragment(ToDosListFragment.newInstance(toDos!!.getData()))
    }

    fun removeTodo(todo: Todo) {
        mainActivityViewModel.removeTodo(this, todo)
    }

    fun saveTodo(todo: Todo) {
        mainActivityViewModel.editTodo(this, todo)
    }

    override fun onBackPressed() {
        val childFragment: Fragment = FragmentManagerHelper.getVisibleFragment(
            supportFragmentManager
        )
        if (childFragment !is ToDosListFragment || !childFragment.onBackPressed()) {
           if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStackImmediate()
                if (supportFragmentManager.backStackEntryCount == 0) {
                    finish()
                }
            } else {
                super.onBackPressed()
            }
        }
    }

    private fun replaceFragment(pFragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.container, pFragment).addToBackStack(null).commit()
    }
}

