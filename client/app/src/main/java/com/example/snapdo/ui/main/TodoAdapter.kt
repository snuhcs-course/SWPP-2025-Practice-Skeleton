package com.example.snapdo.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.snapdo.R
import com.example.snapdo.data.model.Todo

class TodoAdapter(
    private var items: List<Todo>,
    private val onClick: (Todo) -> Unit
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(view: View, val onClick: (Todo) -> Unit) :
        RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.tvTitle)
        private val desc: TextView = view.findViewById(R.id.tvDescription)
        private val verify: TextView = view.findViewById(R.id.tvVerify)

        private val verdict = itemView.findViewById<TextView>(R.id.textVerdict)

        private var current: Todo? = null

        init {
            view.setOnClickListener {
                current?.let { onClick(it) }
            }
        }

        fun bind(todo: Todo) {
            current = todo

            title.text = todo.title
            desc.text = todo.description
            verify.text = todo.target_evidence

            if (todo.verdict != null) {
                verdict.text = todo.verdict
                verdict.setTextColor(
                    if (todo.verdict.equals("PASSED", true))
                        ContextCompat.getColor(itemView.context, android.R.color.holo_green_dark)
                    else
                        ContextCompat.getColor(itemView.context, android.R.color.holo_red_dark)
                )
            } else {
                verdict.text = ""
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newItems: List<Todo>) {
        items = newItems
        notifyDataSetChanged()
    }
}
