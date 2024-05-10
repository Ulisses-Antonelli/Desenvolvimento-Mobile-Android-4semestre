package com.example.recycleview_aula_03

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.recycleview_aula_03.MinhaClasseAdapter
import com.example.recycleview_aula_03.R
import com.example.recycleview_aula_03.model.Card
import com.example.recycleview_aula_03.model.FlashcardDeck
import java.util.*

class MainPrincipal : AppCompatActivity(),  MinhaClasseAdapter.ItemClickListener {


    //procurar por flipcard no youtube para os flashcards

    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: MinhaClasseAdapter
    private var flashcardDecks = mutableListOf<FlashcardDeck>()
    private var backgroundColors = mutableListOf<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        adapter = MinhaClasseAdapter(flashcardDecks,  backgroundColors, this)
        viewPager.adapter = adapter


        val btnAddItem : Button = findViewById<Button>(R.id.btnAddItem)
        btnAddItem.setOnClickListener {
            addFlashcardDeck()
        }
    }

    private fun addFlashcardDeck() {
        val novoDeck = FlashcardDeck("Titulo ${flashcardDecks.size + 1}", mutableListOf() )
        novoDeck.cartoes.add(Card("Insira a Frente do card", "Insira o Verso do Card"))

        backgroundColors.add(getRandomColor())
        flashcardDecks.add(novoDeck)
        adapter.notifyDataSetChanged()
        viewPager.setCurrentItem(flashcardDecks.size - 1, true)
    }

    override fun onDeleteItemClick(position: Int) {
        flashcardDecks.removeAt(position)
        backgroundColors.removeAt(position)
        adapter.notifyDataSetChanged()
    }

    private fun getRandomColor(): Int {
        val rnd = Random()
        return android.graphics.Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }
}
