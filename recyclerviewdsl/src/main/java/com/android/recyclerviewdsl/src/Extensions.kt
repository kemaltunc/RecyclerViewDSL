package com.android.recyclerviewdsl.src

/**
 * Created by Kemal Tunç on 2020-09-30
 */

inline fun <reified T> RecyclerItem.cast(item: (item: T) -> Unit) {
    if (this is T) {
        item(this)
    }
}
