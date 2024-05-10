package com.example.recycleview_aula_03

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleview_aula_03.model.FlashcardDeck

class MinhaClasseAdapter(
    private val flashcardDecks: List<FlashcardDeck>,
    private val backgroundColors: List<Int>,
    private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<MinhaClasseAdapter.MinhaClasseInternaViewHolder>() {

    private val TAG = "MinhaClasseAdapter"

    interface ItemClickListener {
        fun onDeleteItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MinhaClasseInternaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
        return MinhaClasseInternaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MinhaClasseInternaViewHolder, position: Int) {
        holder.bind(flashcardDecks[position], backgroundColors[position])
        Log.d(TAG, "Valor da dataList: ${flashcardDecks[position]}")
        Log.d(TAG, "Background color: ${backgroundColors[position]}")
    }

    override fun getItemCount(): Int {
        return flashcardDecks.size
    }

    inner class MinhaClasseInternaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardView: CardView = itemView.findViewById(R.id.cardView)
        private val textView: TextView = itemView.findViewById(R.id.textView)
        private val editTextTitle: EditText = itemView.findViewById(R.id.editTextTitle)

        private val btnPlay: ImageView = itemView.findViewById(R.id.btnPlay)
        private val btnSave: Button = itemView.findViewById(R.id.btnSave)
        private val btnEdit: ImageView = itemView.findViewById(R.id.btnEdit)
        private val btnDelete: ImageView = itemView.findViewById(R.id.btnDelete)

        init {
            Log.d(TAG, "ViewHolder initialized")
        }

        fun bind(data: FlashcardDeck, bgColor: Int) {
            cardView.setCardBackgroundColor(bgColor)
            textView.text = data.title

            btnPlay.setOnClickListener {
                val intent = Intent(itemView.context, FlashcardActivity::class.java)
                itemView.context.startActivity(intent)
            }

            btnEdit.setOnClickListener {
                editTextTitle.visibility = View.VISIBLE
                editTextTitle.setText(textView.text)
                textView.visibility = View.GONE
            }

            btnSave.setOnClickListener {
                textView.text = editTextTitle.text
                editTextTitle.visibility = View.GONE
                textView.visibility = View.VISIBLE
            }

            btnDelete.setOnClickListener {
                itemClickListener.onDeleteItemClick(adapterPosition)
            }
        }
    }
}