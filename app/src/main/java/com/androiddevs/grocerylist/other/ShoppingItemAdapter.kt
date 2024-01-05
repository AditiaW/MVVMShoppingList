package com.androiddevs.grocerylist.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.grocerylist.R
import com.androiddevs.grocerylist.data.db.entities.ShoppingItem
import com.androiddevs.grocerylist.ui.shoppinglist.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_item.view.*

// Adapter for the RecyclerView to display ShoppingItem data
class ShoppingItemAdapter(
    var items: List<ShoppingItem>, // List of ShoppingItem entities to be displayed
    private val viewModel: ShoppingViewModel // Reference to the ViewModel for handling data operations
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    // Creates and initializes a ViewHolder for each item view in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    // Returns the total number of items in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Binds data to the views in each item of the RecyclerView
    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = items[position]

        // Set the name and amount of the current ShoppingItem to the respective TextViews
        holder.itemView.tvName.text = curShoppingItem.name
        holder.itemView.tvAmount.text = "${curShoppingItem.amount}"

        // Click listeners for delete, plus, and minus buttons
        holder.itemView.ivDelete.setOnClickListener {
            viewModel.delete(curShoppingItem) // Calls ViewModel to delete the ShoppingItem
        }

        holder.itemView.ivPlus.setOnClickListener {
            curShoppingItem.amount++ // Increases the item quantity
            viewModel.upsert(curShoppingItem) // Calls ViewModel to update the ShoppingItem
        }

        holder.itemView.ivMinus.setOnClickListener {
            if (curShoppingItem.amount > 0) {
                curShoppingItem.amount-- // Decreases the item quantity if it's greater than 0
                viewModel.upsert(curShoppingItem) // Calls ViewModel to update the ShoppingItem
            }
        }
    }

    // ViewHolder class holding item views for efficient recycling
    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
