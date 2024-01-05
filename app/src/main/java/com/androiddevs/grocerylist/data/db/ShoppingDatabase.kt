package com.androiddevs.grocerylist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.androiddevs.grocerylist.data.db.entities.ShoppingItem

// Declares this class as a database with the ShoppingItem table
@Database(
    entities = [ShoppingItem::class], // List of entities (tables) within the database
    version = 1 // Database version, useful for handling schema upgrades
)
abstract class ShoppingDatabase : RoomDatabase() {

    // Abstract function providing access to the DAO (Data Access Object)
    abstract fun getShoppingDao(): ShoppingDao

    // Singleton pattern to ensure only one instance of the database exists at a time
    companion object {
        @Volatile
        private var instance: ShoppingDatabase? = null
        private val LOCK = Any()

        // Invoke function that checks the database instance; if null, creates a new instance
        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                instance
                    ?: createDatabase(context).also { instance = it }
            }

        // Function to create a new instance of the Room database
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ShoppingDatabase::class.java,
                "ShoppingDB.db" // Database name
            ).build()
    }
}
