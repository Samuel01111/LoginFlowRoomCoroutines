package br.com.douglasmotta.naivagtioncomponentappmirror.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.douglasmotta.naivagtioncomponentappmirror.data.db.dao.UserDao

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    //function to access every Dao Class
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
