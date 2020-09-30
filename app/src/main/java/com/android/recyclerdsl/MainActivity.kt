package com.android.recyclerdsl

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.recyclerviewdsl.src.CustomAdapter
import com.android.recyclerviewdsl.src.DIRECTION
import com.android.recyclerviewdsl.src.RecyclerItem
import com.android.recyclerviewdsl.src.cast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cell_item.view.*
import kotlinx.android.synthetic.main.cell_item_two.view.*

class MainActivity : AppCompatActivity() {


    class Test(
        var title: String
    ) : RecyclerItem(R.layout.cell_item)

    class Test2(
        var title: String
    ) : RecyclerItem(R.layout.cell_item_two)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = listOf(
            Test(
                "test1"
            ), Test(
                "test2"
            ), Test(
                "test3"
            ), Test2(
                "test4"
            ),
            Test2(
                "test5"
            ), Test2(
                "test6"
            ), Test(
                "test1"
            ), Test2(
                "test6"
            ), Test(
                "test1"
            ), Test2(
                "test6"
            ), Test(
                "test1"
            )

        )

        val adapter = CustomAdapter.build(recyclerview) {
            scrollDirection = DIRECTION.VERTICAL
        }.apply {
            bind { view, s ->

                s.cast<Test>()?.let {
                    blueHolder(view, it)
                }

                s.cast<Test2>()?.let {
                    greenHolder(view, it)
                }

            }
        }

        adapter.items = items
    }


    private fun blueHolder(view: View, item: Test) {
        view.title_tv.text = item.title
    }

    private fun greenHolder(view: View, item: Test2) {
        view.title_tv_2.text = item.title

    }

}


