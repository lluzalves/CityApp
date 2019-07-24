package com.app.daniel.app.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.daniel.app.data.entity.cities.CityEntity
import io.reactivex.Single

@Dao
interface CityDao {
    @Query("select * from city")
    fun getCities(): Single<List<CityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertCity(customerEntity: CityEntity): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertCities(cities: List<CityEntity>): Single<Array<Long>>
}