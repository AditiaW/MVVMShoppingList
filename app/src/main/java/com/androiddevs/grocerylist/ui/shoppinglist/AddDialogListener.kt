package com.androiddevs.grocerylist.ui.shoppinglist

import com.androiddevs.grocerylist.data.db.entities.ShoppingItem

// Interface to communicate item addition from a dialog to the caller
interface AddDialogListener {

    // Method to be implemented by the caller to handle the addition of a ShoppingItem
    fun onAddButtonClicked(item: ShoppingItem)
}
