package com.androiddevs.grocerylist.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.androiddevs.grocerylist.data.db.entities.ShoppingItem

// DAO (Data Access Object) interface for accessing and manipulating ShoppingItem entities in the database
@Dao
interface ShoppingDao {

    // Inserts or replaces a shopping item in the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)

    // Deletes a shopping item from the database
    @Delete
    suspend fun delete(item: ShoppingItem)

    // Retrieves all shopping items from the database
    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems(): LiveData<List<ShoppingItem>>
}
