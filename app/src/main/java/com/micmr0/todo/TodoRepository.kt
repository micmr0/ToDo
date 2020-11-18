package com.micmr0.todo

import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.micmr0.todo.ui.main.MainActivity
import io.reactivex.Observable

class TodoRepository(private val api: ApiService) {
    private val db = Firebase.firestore

    fun getToDos(activity: MainActivity): Observable<ArrayList<Todo>> {
        return Observable.create { emitter ->
            db.collection("todo")
                .get()
                .addOnCompleteListener { result -> {

                    emitter.onNext(result.result?.toObjects(Todo::class.java) as ArrayList<Todo>)
                }

                }
                .addOnFailureListener {
                    Toast.makeText(activity, activity.getString(R.string.error), Toast.LENGTH_SHORT).show()
                }
        }
    }

    fun editTodo(activity: MainActivity, todo: Todo): Observable<ArrayList<Todo>> {
        return Observable.create {
            db.collection("todo")
                .add(todo)
                .addOnSuccessListener {
                    Toast.makeText(
                        activity,
                        activity.getString(R.string.saved),
                        Toast.LENGTH_LONG
                    ).show()
                }.addOnFailureListener {
                    Toast.makeText(activity, activity.getString(R.string.error), Toast.LENGTH_SHORT).show()
                }
        }
    }

    fun removeTodo(activity: MainActivity, todo: Todo): Observable<ArrayList<Todo>> {
        return Observable.create {
            db.collection("todo")
                .document()
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(
                        activity,
                        activity.getString(R.string.saved),
                        Toast.LENGTH_LONG
                    )
                        .show()
                }.addOnFailureListener {
                    Toast.makeText(activity, activity.getString(R.string.error), Toast.LENGTH_SHORT).show()
                }
        }
    }
}