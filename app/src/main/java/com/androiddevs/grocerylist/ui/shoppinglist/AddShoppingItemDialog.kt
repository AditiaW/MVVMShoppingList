package com.androiddevs.grocerylist.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.androiddevs.grocerylist.R
import com.androiddevs.grocerylist.data.db.entities.ShoppingItem
import kotlinx.android.synthetic.main.dialog_add_shopping_item.*

// Custom dialog for adding a new shopping item
class AddShoppingItemDialog(context: Context, var addDialogListener: AddDialogListener) :
    AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Requesting no title for the dialog window
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        // Setting the dialog layout
        setContentView(R.layout.dialog_add_shopping_item)

        // Handling the "Add" button click event
        tvAdd.setOnClickListener {
            val name = etName.text.toString()
            val amount = etAmount.text.toString().toIntOrNull()

            // Validating user input
            if (name.isNullOrEmpty() || amount == null || amount <= 0) {
                Toast.makeText(context, "Please enter valid data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Creating a ShoppingItem object with user input
            val item = ShoppingItem(name, amount)

            // Calling the callback method to add the item
            addDialogListener.onAddButtonClicked(item)

            // Dismissing the dialog after adding the item
            dismiss()
        }

        // Handling the "Cancel" button click event to dismiss the dialog
        tvCancel.setOnClickListener {
            cancel()
        }
    }
}
