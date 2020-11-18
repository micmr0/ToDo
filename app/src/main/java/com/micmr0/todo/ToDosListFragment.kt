package com.micmr0.todo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.micmr0.todo.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_todos_list.*

class ToDosListFragment : Fragment() {
    private lateinit var mainActivity: MainActivity
    private lateinit var currentList: ArrayList<Todo>
    private lateinit var toDos: ArrayList<Todo>


    companion object {
        fun newInstance(toDos: ArrayList<Todo>): ToDosListFragment {
            val fragment = ToDosListFragment()
            val args = Bundle()
            args.putParcelableArrayList("TODOS", toDos)

            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is MainActivity) {
            mainActivity = context
        } else {
            throw RuntimeException(
                String.format(
                    getString(R.string.on_attach_info),
                    TodoFragment::class.java.simpleName,
                    context::class.java.simpleName
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_todos_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentList = ArrayList()

        requireArguments().get("TODOS").let {
            toDos = it as ArrayList<Todo>
        }

        if(toDos.size > 30) {
            val i: MutableIterator<Todo> = toDos.iterator()
            while (i.hasNext()) {
                val s = i.next()
                currentList.add(s)
                i.remove()
            }
        } else {
            currentList = toDos
        }

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ListAdapter(currentList, object : ListAdapter.OnTodoItemClickListener {
                override fun onItemClicked(todo: Todo) {
                    childFragmentManager.beginTransaction().replace(
                        R.id.container, TodoFragment.newInstance(
                            todo
                        )
                    ).addToBackStack(null).commit()

                    fab.hide()
                }

                override fun onItemRemove(todo: Todo) {
                    mainActivity.removeTodo(todo)
                }
            })
        }

        load_more.setOnClickListener {
            if(toDos.size > 30) {
                val i: MutableIterator<Todo> = toDos.iterator()
                while (i.hasNext()) {
                    val s = i.next()
                    currentList.add(s)
                    i.remove()
                }
            }else {
                Toast.makeText(activity, getString(R.string.no_more), Toast.LENGTH_SHORT).show()
            }
        }

        activity?.title = getString(R.string.todos_list)

        fab.setOnClickListener {
            childFragmentManager.beginTransaction().replace(
                R.id.container, TodoFragment.newInstance(
                    Todo()
                )
            ).addToBackStack(null).commit()

            fab.hide()
        }
    }

    override fun onResume() {
        super.onResume()
        fab.show()
    }

    fun onBackPressed(): Boolean {
        return if (childFragmentManager.backStackEntryCount > 0) {
            childFragmentManager.popBackStack()
            true
        } else {
            false
        }
    }
}