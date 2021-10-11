package com.inspiredcoda.cookbook.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.inspiredcoda.cookbook.data.response.FruitData
import com.inspiredcoda.cookbook.databinding.FruitsItemBinding

class FruitAdapter(
    val action: (FruitData) -> Unit
) : RecyclerView.Adapter<FruitAdapter.FruitViewHolder>() {

    private var fruits = mutableListOf<FruitData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        val layout = FruitsItemBinding.inflate(LayoutInflater.from(parent.context))
        return FruitViewHolder(layout)
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        if (fruits.isNotEmpty()) {
            val fruit = fruits[position]
            val context = holder.image.context
            val id = context.resources.getIdentifier(fruit.image, "drawable", context.packageName)
            val drawable = context.resources.getDrawable(id, context.theme)
            Glide.with(holder.itemView.context)
                .load(drawable)
                .into(holder.image)
            holder.fruitName.text = fruits[position].productName
            holder.container.setOnClickListener {
                action.invoke(fruits[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return fruits.size
    }

    fun submitFruits(newFruits: List<FruitData>) {
        if (newFruits.isNotEmpty()) {
            fruits.addAll(newFruits)
            notifyDataSetChanged()
        }
    }

    inner class FruitViewHolder(root: FruitsItemBinding) : RecyclerView.ViewHolder(root.root) {
        var container = root.root
        var image = root.fruitPic
        var fruitName = root.fruitName
    }

}