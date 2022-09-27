package den.kotlinspringhttprestapijdbc.service
import den.kotlinspringhttprestapijdbc.model.*

interface UserService {
    fun getAllData(): MutableList<User>
    fun getData(id: String): List<User>
    fun createUser(userId: String, userName: String, userAddress: String, userEmail: String, userPassword: String) : Message
    fun updateUser(wuserId: String, userId: String, userName: String, userAddress: String, userEmail: String, userPassword: String ) : Message
    fun userDelete(id: String)
}