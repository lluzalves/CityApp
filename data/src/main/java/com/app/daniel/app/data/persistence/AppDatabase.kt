package com.app.daniel.app.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.daniel.app.data.entity.cities.CityEntity
import com.app.daniel.app.data.persistence.dao.CityDao

@Database(entities = [CityEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val cityDao: CityDao

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