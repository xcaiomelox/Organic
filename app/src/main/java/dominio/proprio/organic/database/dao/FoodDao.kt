package dominio.proprio.organic.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import dominio.proprio.organic.model.Food

@Dao
interface FoodDao {

    @Query("SELECT * FROM food")
    fun getAll(): List<Food>

    @Insert(onConflict = REPLACE)
    fun save(food: Food)

    @Delete
    fun delete(food: Food)

    @Query("SELECT * FROM food WHERE id = :id")
    fun searchForId(id: Long): List<Food>
}