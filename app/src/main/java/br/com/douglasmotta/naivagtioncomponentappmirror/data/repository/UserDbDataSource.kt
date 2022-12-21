package br.com.douglasmotta.naivagtioncomponentappmirror.data.repository

import br.com.douglasmotta.naivagtioncomponentappmirror.data.db.dao.UserDao
import br.com.douglasmotta.naivagtioncomponentappmirror.data.db.toUser
import br.com.douglasmotta.naivagtioncomponentappmirror.data.db.toUserEntity
import br.com.douglasmotta.naivagtioncomponentappmirror.data.model.User
import br.com.douglasmotta.naivagtioncomponentappmirror.ui.registration.RegistrationViewParams

//implementing our DB data source (it could be API data source)
class UserDbDataSource(
    private val userDao: UserDao
): UserRepository {
    //suspend indicates that this function can be used in coroutines scope.
    override suspend fun createUser(registrationViewParams: RegistrationViewParams) {
        val userEntity = registrationViewParams.toUserEntity()
        userDao.save(userEntity)
    }

    override suspend fun getUser(id: Long): User {
        return userDao.getUser(id).toUser()
    }

    override fun login(username: String, password: String): Long {
        return userDao.login(username, password)
    }
}
