package com.app.daniel.app.data.entity.cities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.daniel.app.data.entity.BaseEntity

@Entity(tableName = CityEntity.NAME)
data class CityEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = Columns.ID)
    override var id: Long = System.currentTimeMillis(),

    @ColumnInfo(name = Columns.CODIGO_CIDADE)
    var code: String,

    @ColumnInfo(name = Columns.CIDADE)
    var name: String,

    @ColumnInfo(name = Columns.UF)
    var uf: String,

    @ColumnInfo(name = Columns.CREATED_AT)
    override var createdAt: String,

    @ColumnInfo(name = Columns.UPDATED_AT)
    override var updatedAt: String
) : BaseEntity {

    companion object {
        const val NAME = "orders"

        object Columns {
            const val ID = "id"
            const val CODIGO_CIDADE = "codCidade"
            const val CIDADE = "cidade"
            const val UF = "uf"
            const val CREATED_AT = "created_at"
            const val UPDATED_AT = "updated_at"
        }
    }
}
