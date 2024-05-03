package com.example.recycleview_aula_03

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.recycleview_aula_03.model.Card
import com.example.recycleview_aula_03.model.FlashcardDeck

class FlashcardActivity : AppCompatActivity() {

    private lateinit var cardFrenteTextView: TextView
    private lateinit var cardVersoTextView: TextView
    private lateinit var btn_Acerto: Button
    private lateinit var btn_Erro: Button

    private lateinit var flashcardDeck: FlashcardDeck
    private var currentCardIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcard)

        cardFrenteTextView = findViewById(R.id.card_question)
        cardVersoTextView = findViewById(R.id.card_answer)
        btn_Acerto = findViewById(R.id.button_correct)
        btn_Erro = findViewById(R.id.button_wrong)

        flashcardDeck = initializeFlashcardDeck()
        displayCard()

        btn_Acerto.setOnClickListener { onNextCardClicked() }
        btn_Erro.setOnClickListener { onNextCardClicked() }
    }

    private fun initializeFlashcardDeck(): FlashcardDeck {
        // Aqui você inicializa o baralho com suas cartas
        val cards = mutableListOf(
            Card("Pergunta 1", "Resposta 1"),
            Card("Pergunta 2", "Resposta 2"),
            Card("Pergunta 3", "Resposta 3")
            // Adicione mais cartas conforme necessário
        )
        return FlashcardDeck("Meu Baralho", cards)
    }

    private fun displayCard() {
        val currentCard = flashcardDeck.cartoes[currentCardIndex]
        cardFrenteTextView.text = currentCard.frente
        cardVersoTextView.text = currentCard.verso
    }

    private fun onNextCardClicked() {
        currentCardIndex = (currentCardIndex + 1) % flashcardDeck.cartoes.size
        displayCard()
    }
}