package com.app.daniel.app.data.entity.cities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.daniel.app.data.entity.BaseEntity
import com.google.gson.annotations.SerializedName

@Entity(tableName = CityEntity.NAME)
data class CityEntity(
    @PrimaryKey
    @NonNull
    @SerializedName(CODIGO_CIDADE)
    @ColumnInfo(name = CODIGO_CIDADE)
    override var code: String,

    @SerializedName(CIDADE)
    @ColumnInfo(name = CIDADE)
    var name: String,

    @SerializedName(UF)
    @ColumnInfo(name = UF)
    var uf: String
) : BaseEntity {

    companion object {
        const val NAME = "city"
        const val CODIGO_CIDADE = "codCidade"
        const val CIDADE = "cidade"
        const val UF = "uf"
    }
}
