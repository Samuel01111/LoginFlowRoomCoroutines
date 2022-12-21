package br.com.douglasmotta.naivagtioncomponentappmirror.data.repository

import br.com.douglasmotta.naivagtioncomponentappmirror.data.model.User
import br.com.douglasmotta.naivagtioncomponentappmirror.ui.registration.RegistrationViewParams

interface UserRepository {

    suspend fun createUser(registrationViewParams: RegistrationViewParams)

    suspend fun getUser(id: Long): User

    fun login(username: String, password: String): Long
}
