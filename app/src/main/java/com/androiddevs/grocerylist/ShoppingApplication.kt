package com.androiddevs.grocerylist

import android.app.Application
import com.androiddevs.grocerylist.data.db.ShoppingDatabase
import com.androiddevs.grocerylist.data.repositories.ShoppingRepository
import com.androiddevs.grocerylist.ui.shoppinglist.ShoppingViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ShoppingApplication : Application(), KodeinAware {

    // Implementing KodeinAware to provide Kodein instance
    override val kodein: Kodein = Kodein.lazy {
        // Importing AndroidX module for Kodein Android support
        import(androidXModule(this@ShoppingApplication))

        // Singleton bindings for ShoppingDatabase, ShoppingRepository, and ShoppingViewModelFactory
        bind() from singleton { ShoppingDatabase(instance()) } // Provides a single instance of ShoppingDatabase
        bind() from singleton { ShoppingRepository(instance()) } // Provides a single instance of ShoppingRepository
        bind() from provider { // Provides a ShoppingViewModelFactory instance on demand
            ShoppingViewModelFactory(instance())
        }
    }
}
