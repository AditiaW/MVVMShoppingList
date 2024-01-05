package com.androiddevs.grocerylist.ui.shoppinglist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.androiddevs.grocerylist.R
import com.androiddevs.grocerylist.other.ShoppingItemAdapter
import com.androiddevs.grocerylist.data.db.entities.ShoppingItem
import kotlinx.android.synthetic.main.activity_shopping.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

// Activity displaying a shopping list and handling item addition
class ShoppingActivity : AppCompatActivity(), KodeinAware {

    // Using Kodein for dependency injection
    override val kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()

    lateinit var viewModel: ShoppingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        // Initializing ViewModel using ViewModelProvider with the provided factory
        viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)

        // Initializing the RecyclerView adapter
        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        // Setting up RecyclerView with a LinearLayoutManager and the initialized adapter
        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        // Observing changes in the shopping items LiveData from the ViewModel
        viewModel.getAllShoppingItems().observe(this, Observer {
            // Update the adapter's items list and notify changes when the observed data changes
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        // Handling the click event of the FAB (Floating Action Button) for adding items
        fab.setOnClickListener {
            // Displaying a dialog to add a new shopping item
            AddShoppingItemDialog(
                this,
                object : AddDialogListener {
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        // Calling ViewModel's method to add or update the shopping item
                        viewModel.upsert(item)
                    }
                }).show()
        }
    }
}
