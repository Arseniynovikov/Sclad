package com.example.sklad.data.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sklad.data.database.dao.CategoryDao
import com.example.sklad.data.database.dao.ProductDao
import com.example.sklad.data.database.dao.TransactionDao
import com.example.sklad.data.database.dao.UserDao
import com.example.sklad.data.database.dao.WarehouseDao
import com.example.sklad.data.database.entities.Category
import com.example.sklad.data.database.entities.Product
import com.example.sklad.data.database.entities.Transaction
import com.example.sklad.data.database.entities.User
import com.example.sklad.data.database.entities.Warehouse

@Database(
    entities = [
        User::class,
        Warehouse::class,
        Product::class,
        Category::class,
        Transaction::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun warehouseDao(): WarehouseDao
    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "inventory_database" // имя базы данных
                ).build()
                INSTANCE = instance
                Log.d("AppDatabase", "Database created: inventory_database")
                instance
            }
        }
    }
}
