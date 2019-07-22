package com.daniel.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.daniel.data.entity.customer.CustomerEntity
import com.app.daniel.app.data.entity.cities.CityEntity
import com.daniel.data.entity.product.ProductEntity

@Database(entities = [CustomerEntity::class, CityEntity::class, ProductEntity::class], version = 1)
@TypeConverters(CityEntity.ProductConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val customerDao: CustomerDao
    abstract val orderDao: OrderDao
    abstract val productDao : ProductDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room
                        .databaseBuilder(context, AppDatabase::class.java, "app_db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyDatabase() {
            INSTANCE = null
        }
    }
}