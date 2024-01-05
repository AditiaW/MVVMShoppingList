package com.androiddevs.grocerylist.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity annotation marks this class as a table in the Room database named "shopping_items"
@Entity(tableName = "shopping_items")
data class ShoppingItem(
    // ColumnInfo annotation specifies details about the column in the database table
    @ColumnInfo(name = "item_name")
    var name: String,  // Represents the name of the shopping item

    @ColumnInfo(name = "item_amount")
    var amount: Int    // Represents the quantity/amount of the shopping item
) {
    // PrimaryKey annotation marks the 'id' field as the primary key for this table
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null // Represents the unique identifier for the shopping item, set to null by default
}
