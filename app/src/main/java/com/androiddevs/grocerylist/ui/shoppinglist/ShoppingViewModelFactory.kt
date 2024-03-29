package com.androiddevs.grocerylist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androiddevs.grocerylist.data.repositories.ShoppingRepository

@Suppress("UNCHECKED_CAST")
class ShoppingViewModelFactory(
    private val repository: ShoppingRepository
) : ViewModelProvider.NewInstanceFactory() {

    // Overrides the create method to instantiate ShoppingViewModel with the required dependencies
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        // Constructs and returns a ShoppingViewModel instance
        return ShoppingViewModel(repository) as T
        // The 'repository' instance is passed to the ShoppingViewModel constructor
    }
}
