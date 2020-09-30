package com.android.recyclerviewdsl.src

inline fun <reified T> RecyclerItem.cast(): T? {
    return if (this is T) {
        this
    } else {
        null
    }
}
