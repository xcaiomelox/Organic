package dominio.proprio.organic.adapter

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dominio.proprio.organic.database.dao.FoodDao
import dominio.proprio.organic.model.Food

@Database(
    version = 2,
    entities = [Food::class]
)

abstract class AppDataBase : RoomDatabase() {
    abstract fun foodDao(): FoodDao


    companion object {
        @Volatile
        private var db: AppDataBase? = null

        fun instance(context: Context?): AppDataBase {
            if (db == null) {
                db = context?.let {
                    Room.databaseBuilder(
                        it,
                        AppDataBase::class.java,
                        "database-Organic"
                    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
                }
            }
            return db!!
        }
    }
}