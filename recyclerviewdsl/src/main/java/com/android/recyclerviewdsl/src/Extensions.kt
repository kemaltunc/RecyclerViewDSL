package com.android.recyclerviewdsl.src

/**
 * Created by Kemal Tunç on 2020-09-30
 */

inline fun <reified T> RecyclerItem.cast(item: (item: T) -> Unit) {
    if (this is T) {
        item(this)
    }
}


@Suppress("UNCHECKED_CAST")
inline fun <reified T : Any> List<*>.cast() =
    if (all { it is T })
        this as List<T>
    else emptyList()