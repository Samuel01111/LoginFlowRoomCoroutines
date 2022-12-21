package br.com.douglasmotta.naivagtioncomponentappmirror.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.douglasmotta.naivagtioncomponentappmirror.data.model.User
import br.com.douglasmotta.naivagtioncomponentappmirror.ui.registration.RegistrationViewParams

@Entity(tableName = "user") // it gonna be converted to a table into DB
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val bio: String,
    val username: String,
    val password: String
)

fun RegistrationViewParams.toUserEntity(): UserEntity {
    return UserEntity(
            name = this.name,
            bio = this.bio,
            username = this.userName,
            password = this.password
        )
}

fun UserEntity.toUser(): User {
    return User(
        this.id.toString(),
        this.name,
        this.bio
    )
}
