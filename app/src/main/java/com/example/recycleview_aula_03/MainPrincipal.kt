package com.example.recycleview_aula_03

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.recycleview_aula_03.MinhaClasseAdapter
import com.example.recycleview_aula_03.R
import java.util.*

class MainPrincipal : AppCompatActivity(),  MinhaClasseAdapter.ItemClickListener {


    //procurar por flipcard no youtube para os flashcards

    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: MinhaClasseAdapter
    private var dataList = mutableListOf<String>()
    //desfez a variavel backgroundColors
    private var backgroundColors = mutableListOf<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        adapter = MinhaClasseAdapter(dataList,  backgroundColors, this)
        viewPager.adapter = adapter


        val btnAddItem : Button = findViewById<Button>(R.id.btnAddItem)
        btnAddItem.setOnClickListener {
            addItemToList()
        }
    }


    private fun addItemToList() {
        dataList.add("Item ${dataList.size + 1}")
        backgroundColors.add(getRandomColor())
        adapter.notifyDataSetChanged()
        viewPager.setCurrentItem(dataList.size - 1, true)
    }

    override fun onDeleteItemClick(position: Int) {
        dataList.removeAt(position)
        backgroundColors.removeAt(position)
        adapter.notifyDataSetChanged()
    }

    private fun getRandomColor(): Int {
        val rnd = Random()
        return android.graphics.Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }
}
