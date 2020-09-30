package com.android.recyclerdsl

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.recyclerviewdsl.src.CustomAdapter
import com.android.recyclerviewdsl.src.DIRECTION
import com.android.recyclerviewdsl.src.RecyclerItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cell_item.view.*

class MainActivity : AppCompatActivity() {


    class Test(
        var title: String
    ) : RecyclerItem(R.layout.cell_item)

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
            )

        )

        val adapter = CustomAdapter.build<Test>(recyclerview) {
            scrollDirection = DIRECTION.VERTICAL
        }.apply {

            bind { view, s ->
                view.title_tv.text = s.title
            }
            onItemClickListener { s, i ->
                Toast.makeText(this@MainActivity, s.title, Toast.LENGTH_LONG).show()
            }

        }

        adapter.items = items


    }

}


