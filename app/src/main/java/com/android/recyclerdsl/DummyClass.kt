package com.android.recyclerdsl

import com.android.recyclerviewdsl.src.RecyclerItem


class MainModel(
    var list: List<RecyclerItem> = emptyList(),
    var type: PostType
) : RecyclerItem(R.layout.cell_item)


class Post(
    var title: String = "post"
) : RecyclerItem(R.layout.cell_post)

class Story(
    var title: String = "story"
) : RecyclerItem(R.layout.cell_story)

class Card(
    var title: String = "card"
) : RecyclerItem(R.layout.cell_card)

enum class PostType {
    STORY, CARD
}

fun getList(): List<RecyclerItem> {

    return listOf(
        MainModel(getStoryList(), PostType.STORY),
        Post(),
        MainModel(getCardList(), PostType.CARD),
        Post(),
        Post(),
        Post(),
        Post(),
        Post(),
        Post()
    )

}


fun getPostList() = listOf(
    Post(),
    Post(),
    Post(),
    Post(),
    Post()
)


fun getStoryList() = listOf(
    Story(),
    Story(),
    Story(),
    Story(),
    Story(),
    Story(),
    Story(),
    Story(),
    Story(),
    Story(),
    Story(),
    Story()
)

fun getCardList() = listOf(
    Card(),
    Card(),
    Card(),
    Card(),
    Card(),
    Card(),
    Card(),
    Card(),
    Card()
)