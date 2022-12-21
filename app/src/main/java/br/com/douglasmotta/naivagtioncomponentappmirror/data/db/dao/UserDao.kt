package br.com.douglasmotta.naivagtioncomponentappmirror.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.douglasmotta.naivagtioncomponentappmirror.data.db.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(user: UserEntity)

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUser(id: Long): UserEntity

    @Query("SELECT id FROM user WHERE username = :username and password = :password")
    fun login(username: String, password: String): Long

}