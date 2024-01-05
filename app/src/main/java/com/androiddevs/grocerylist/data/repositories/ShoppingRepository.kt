package com.androiddevs.grocerylist.data.repositories

import com.androiddevs.grocerylist.data.db.entities.ShoppingItem
import com.androiddevs.grocerylist.data.db.ShoppingDatabase

// Repository class responsible for handling operations related to ShoppingItem entities
class ShoppingRepository(
    private val db: ShoppingDatabase // Injecting ShoppingDatabase dependency via constructor
) {
    // Suspend function to insert or update a ShoppingItem in the database
    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)

    // Suspend function to delete a ShoppingItem from the database
    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    // Function to fetch all ShoppingItems from the database
    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()
}
