package com.androiddevs.grocerylist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.androiddevs.grocerylist.data.db.entities.ShoppingItem
import com.androiddevs.grocerylist.data.repositories.ShoppingRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// ViewModel class for managing data related to the shopping list
class ShoppingViewModel(
    private val repository: ShoppingRepository
) : ViewModel() {

    // Function to insert or update a shopping item in the database using coroutines
    fun upsert(item: ShoppingItem) =
        GlobalScope.launch {
            repository.upsert(item)
        }

    // Function to delete a shopping item from the database using coroutines
    fun delete(item: ShoppingItem) = GlobalScope.launch {
        repository.delete(item)
    }

    // Function to get all shopping items from the database
    fun getAllShoppingItems() = repository.getAllShoppingItems()

}
