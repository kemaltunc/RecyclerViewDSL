package com.android.recyclerdsl

import com.android.recyclerviewdsl.src.RecyclerItem

class DummyClassOne(
    var title: String
) : RecyclerItem(R.layout.cell_item)

class DummyClassTwo(
    var title: String
) : RecyclerItem(R.layout.cell_item_two)


fun getList(): List<RecyclerItem> {
    return listOf(
        DummyClassOne(
            "test1"
        ), DummyClassOne(
            "test2"
        ), DummyClassOne(
            "test3"
        ), DummyClassTwo(
            "test4"
        ),
        DummyClassTwo(
            "test5"
        ), DummyClassTwo(
            "test6"
        ), DummyClassTwo(
            "test1"
        ), DummyClassOne(
            "test1"
        ), DummyClassTwo(
            "test6"
        ), DummyClassTwo(
            "test1"
        ), DummyClassTwo(
            "test6"
        ), DummyClassOne(
            "test1"
        )
    )
}