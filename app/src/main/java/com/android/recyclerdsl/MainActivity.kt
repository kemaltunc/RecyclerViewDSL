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




        val adapter = CustomAdapter.build<RecyclerItem>(recyclerview) {
            scrollDirection = DIRECTION.VERTICAL
        }.apply {

            bind { itemView, item, adapterPosition ->

                item.cast<MainModel> {
                    mainHolder(itemView, it)
                    return@bind
                }

                item.cast<Post> {
                    postHolder(itemView, it, adapterPosition)
                    return@bind
                }

            }
        }

        adapter.items = getList()
    }


    private fun mainHolder(view: View, item: MainModel) {
        when (item.type) {
            PostType.STORY -> {
                storyHolder(view, item.list.cast())
            }
            PostType.CARD -> {
                cardHolder(view, item.list.cast())
            }
        }
    }


    private fun postHolder(view: View, item: Post, position: Int) {
        with(view) {
            postTitle.text = item.title
        }
    }

    private fun storyHolder(view: View, items: List<Story>) {
        val adapter = CustomAdapter.build<Story>(view.inner_rv) {
            scrollDirection = DIRECTION.HORIZANTAL
        }.apply {

            bind { itemView, item, adapterPosition ->
                with(itemView) {
                    storyTitle.text = item.title
                }
            }

            this.items = items
        }

    }

    private fun cardHolder(view: View, items: List<Card>) {
        val adapter = CustomAdapter.build<Card>(view.inner_rv) {
            scrollDirection = DIRECTION.GRID
            column = 2
        }.apply {

            bind { itemView, item, adapterPosition ->
                with(itemView) {
                    cardTitle.text = item.title
                }
            }
            onItemClickListener { item, adapterPosition ->

            }

            this.items = items
        }
    }

}

