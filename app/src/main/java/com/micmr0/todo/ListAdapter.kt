package com.micmr0.todo


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class ListAdapter(
    private val listData: ArrayList<Todo>,
    private val onItemClickListener: OnTodoItemClickListener
) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item, parent, false)

        return ViewHolder(listItem)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo: Todo = listData[position]

        holder.container.setOnLongClickListener {

            Snackbar.make(holder.container, holder.container.context.getString(R.string.remove_ask), Snackbar.LENGTH_LONG)
                .setAction(holder.container.context.getString(R.string.remove)) {
                    onItemClickListener.onItemRemove(todo)
                }.show()
            true
        }

        holder.container.setOnClickListener {
            onItemClickListener.onItemClicked(todo)
        }


        if (!todo.icon.isNullOrEmpty()) {
            Picasso.get()
                .load(todo.icon)
                .fit()
                .into(holder.iconImageView)
        }

        if (listData[position].title!!.length <= 30) {
            holder.titleTextView.text = listData[position].title
        } else {
            holder.titleTextView.text = listData[position].title?.substring(0, 30) + "..."
        }

        if (listData[position].description!!.length <= 30) {
            holder.descriptionTextView.text = listData[position].description
        } else {
            holder.descriptionTextView.text =
                "${listData[position].description?.substring(0, 30)}..."
        }

        holder.dateTextView.text = listData[position].date.toString()
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var container: View = itemView.findViewById(R.id.root)
        var iconImageView: ImageView = itemView.findViewById<View>(R.id.icon) as ImageView
        var titleTextView: TextView = itemView.findViewById<View>(R.id.title) as TextView
        var descriptionTextView: TextView =
            itemView.findViewById<View>(R.id.description) as TextView
        var dateTextView: TextView = itemView.findViewById<View>(R.id.date) as TextView

    }

    interface OnTodoItemClickListener {
        fun onItemClicked(todo: Todo)
        fun onItemRemove(todo: Todo)
    }
}