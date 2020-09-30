package com.android.recyclerdsl

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.recyclerviewdsl.src.CustomAdapter
import com.android.recyclerviewdsl.src.DIRECTION
import com.android.recyclerviewdsl.src.cast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cell_item.view.*
import kotlinx.android.synthetic.main.cell_item_two.view.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val adapter = CustomAdapter.build(recyclerview) {
            scrollDirection = DIRECTION.VERTICAL
        }.apply {
            bind { view, s, i ->

                s.cast<DummyClassOne>()?.let {
                    blueHolder(view, it, i)
                    return@bind
                }

                s.cast<DummyClassTwo>()?.let {
                    greenHolder(view, it, i)
                    return@bind
                }
            }
        }

        adapter.items = getList()
    }


    private fun blueHolder(view: View, item: DummyClassOne, position: Int) {
        view.title_tv.text = item.title
    }

    private fun greenHolder(view: View, item: DummyClassTwo, position: Int) {
        view.title_tv_2.text = item.title
    }

}


