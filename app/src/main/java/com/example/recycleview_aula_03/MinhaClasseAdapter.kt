package com.example.recycleview_aula_03

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class MinhaClasseAdapter(
    private val dataList: List<String>,
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
        holder.bind(dataList[position], backgroundColors[position])
        Log.d(TAG, "Valor da dataList: ${dataList[position]}")
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class MinhaClasseInternaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardView: CardView = itemView.findViewById(R.id.cardView)
        private val textView: TextView = itemView.findViewById(R.id.textView)
        private val btnPlay: ImageView = itemView.findViewById(R.id.btnPlay)
        private val btnDelete: ImageView = itemView.findViewById(R.id.btnDelete)

        fun bind(data: String, bgColor: Int) {
            cardView.setCardBackgroundColor(bgColor)
            textView.text = data

            btnPlay.setOnClickListener {
                // Ação do botão de reprodução (ainda não implementado)
            }

            btnDelete.setOnClickListener {
                itemClickListener.onDeleteItemClick(adapterPosition)
            }
        }
    }
}