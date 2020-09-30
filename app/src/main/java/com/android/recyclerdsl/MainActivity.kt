package com.android.recyclerdsl

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.recyclerviewdsl.src.CustomAdapter
import com.android.recyclerviewdsl.src.DIRECTION
import com.android.recyclerviewdsl.src.RecyclerItem
import com.android.recyclerviewdsl.src.cast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cell_card.view.*
import kotlinx.android.synthetic.main.cell_item.view.*
import kotlinx.android.synthetic.main.cell_post.view.*
import kotlinx.android.synthetic.main.cell_story.view.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val adapter = CustomAdapter.build(recyclerview) {
            scrollDirection = DIRECTION.VERTICAL
        }.apply {
            /***
             * @param view; itemView
             * @param s; item
             * @param i; adapterPosition
             * */
            bind { view, s, i ->

                s.cast<MainModel> {
                    mainHolder(view, it)
                    return@bind
                }

                s.cast<Post> {
                    postHolder(view, it, i)
                    return@bind
                }

            }

        }

        adapter.items = getList()
    }


    private fun mainHolder(view: View, item: MainModel) {
        when (item.type) {
            PostType.STORY -> {
                storyHolder(view, item.list)
            }
            PostType.CARD -> {
                cardHolder(view, item.list)
            }
        }
    }

    private fun postHolder(view: View, item: Post, position: Int) {
        with(view) {
            postTitle.text = item.title
        }
    }

    private fun storyHolder(view: View, items: List<RecyclerItem>) {
        val adapter = CustomAdapter.build(view.inner_rv) {
            scrollDirection = DIRECTION.HORIZANTAL
        }.apply {
            bind { view, s, i ->
                s.cast<Story> {
                    with(view) {

                        storyTitle.text = it.title

                        setOnClickListener {

                        }
                    }
                }
            }
        }

        adapter.items = items
    }

    private fun cardHolder(view: View, items: List<RecyclerItem>) {
        val adapter = CustomAdapter.build(view.inner_rv) {
            scrollDirection = DIRECTION.GRID
            column = 2
        }.apply {
            bind { view, s, i ->
                s.cast<Card> {
                    with(view) {
                        cardTitle.text = it.title
                    }
                }
            }
        }

        adapter.items = items
    }

}

