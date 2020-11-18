package com.micmr0.todo

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.micmr0.todo.ui.main.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_todo.*
import java.util.*

class TodoFragment : Fragment() {
    private lateinit var mainActivity: MainActivity
    private lateinit var toDo: Todo

    companion object {
        fun newInstance(toDo: Todo): TodoFragment {
            val fragment = TodoFragment()
            val args = Bundle()
            args.putParcelable("TODO", toDo)

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
        return inflater.inflate(R.layout.fragment_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireArguments().get("TODO").let {
            toDo = it as Todo
        }

        val todo = Todo()

        if (!toDo.icon.isNullOrEmpty()) {
            Picasso.get().load(toDo.icon).into(icon)
        }

        title_edit_text.setText(toDo.title)
        description_edit_text.setText(toDo.description)

        photo.setOnClickListener {
            val builder = AlertDialog.Builder(activity)

            val input = EditText(activity)
            input.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            builder.setView(input)

            builder.setPositiveButton(getString(R.string.ok)) { _, _ ->
                run {
                    todo.icon = input.text.toString()
                }
            }
            builder.setNegativeButton(
                getString(R.string.cancel)
            ) { dialog, _ -> dialog.cancel() }

            builder.show()
        }

        save_button.setOnClickListener {
            todo.title = title_edit_text.text.toString()
            todo.description = description_edit_text.text.toString()
            todo.date = Date()
            todo.date?.time = System.currentTimeMillis()

            mainActivity.saveTodo(todo)
        }

        activity?.title = "Todo task";
    }
}

