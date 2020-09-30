package com.android.recyclerviewdsl.src

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import kotlin.properties.Delegates


class CustomAdapter<T : RecyclerItem>(
    private val recyclerView: RecyclerView,
    scrollDirection: DIRECTION,
    column: Int
) : ListAdapter<T, CustomAdapter.DataClassViewHolder<T>>(DiffCallback<T>()) {

    private val context: Context
        get() = recyclerView.context


    private constructor(builder: Builder) : this(
        builder.recyclerView,
        builder.scrollDirection,
        builder.column
    )

    private var _onBindViewHolder: (view: View, item: T) -> Unit = { _, _ -> }
    private var _onItemClickListener: (item: T, position: Int) -> Unit = { _, _ -> }
    private var _position: (position: Int) -> Unit = { _ -> }
    private var _bottomDetect: () -> Unit = { }

    lateinit var item: T

    init {

        val mLayoutManager = when (scrollDirection) {
            DIRECTION.VERTICAL -> {
                LinearLayoutManager(context)
            }
            DIRECTION.HORIZANTAL -> {
                LinearLayoutManager(context)
            }
            DIRECTION.GRID -> {
                GridLayoutManager(context, column)
            }
        }

        recyclerView.apply {
            layoutManager = mLayoutManager
            adapter = this@CustomAdapter
        }

    }


    var items: List<T> by Delegates.observable(emptyList()) { prop, old, new ->
        this.submitList(items)
    }


    fun bind(f: (View, T) -> Unit) {
        _onBindViewHolder = f
    }

    override fun getItem(position: Int): T {
        item = items[position]
        return items[position]
    }

    fun onItemClickListener(f: (T, Int) -> Unit) {
        _onItemClickListener = f
    }

    fun bottomDetect(f: () -> Unit) {
        _bottomDetect = f
    }

    fun adapterPosition(f: (Int) -> Unit) {
        _position = f
    }



    override fun getItemViewType(position: Int): Int {
        _position(position)
        return items[position].resId
    }

    class DataClassViewHolder<T>(
        itemView: View,
        private val _onItemClickListener: (item: T, position: Int) -> Unit,
        private val _onBindViewHolder: (view: View, item: T) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: T) {
            _onBindViewHolder(itemView.rootView, item)

            itemView.setOnClickListener {
                _onItemClickListener(item, adapterPosition)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataClassViewHolder<T> {

        val itemView: View = LayoutInflater.from(parent.context).inflate(viewType, parent, false)

        return DataClassViewHolder(
            itemView,
            _onItemClickListener,
            _onBindViewHolder
        )

    }


    override fun onBindViewHolder(holder: DataClassViewHolder<T>, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    class DiffCallback<Data> : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data) =
            oldItem.hashCode() == newItem.hashCode()

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Data, newItem: Data) = oldItem == newItem
    }

    companion object {
        inline fun build(
            recyclerView: RecyclerView,
            block: Builder.() -> Unit
        ) = Builder(recyclerView).apply(block).build()

    }

    class Builder(
        val recyclerView: RecyclerView
    ) {
        var scrollDirection: DIRECTION = DIRECTION.VERTICAL
        var column = 1

        fun build() = CustomAdapter<RecyclerItem>(this)
    }
}

