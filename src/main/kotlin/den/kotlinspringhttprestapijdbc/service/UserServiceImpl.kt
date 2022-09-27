package den.kotlinspringhttprestapijdbc.service
import den.kotlinspringhttprestapijdbc.model.*
import den.kotlinspringhttprestapijdbc.config.Config
import org.springframework.stereotype.Service
import java.sql.ResultSet

@Service
class UserServiceImpl: UserService {

        override fun createUser(userId: String, userName: String, userAddress: String, userEmail: String, userPassword: String): Message {
            //procedural approach
            val conn = Config().connect()
            val query = conn?.prepareStatement("INSERT INTO users(id, name, address, email, password) " +
                    "VALUES('$userId', '$userName', '$userAddress','$userEmail','$userPassword')")
            if (query != null) {
                query.executeUpdate()
                return Message("Successfully Creation")
            }
            return Message("Not Successful")

            //functional approach
            /*Config().connect().use { con ->
                con.prepareStatement(
                    "INSERT INTO users(id, name, address, email, password)" +
                            " VALUES('$userId', '$userName', '$userAddress','$userEmail','$userPassword')"
                ).use { result ->
                    result.executeUpdate()
                         Message("Successfully Created")
                }

            }
            return Message("Not Successful")
        */
        }

        override fun updateUser(wuserId: String, userId: String, userName: String, userAddress: String, userEmail: String, userPassword: String ) : Message {
            //procedural approach
            val conn = Config().connect()
            val query = conn?.prepareStatement("UPDATE users SET id='$userId', name='$userName', address='$userAddress', email='$userEmail', password='$userPassword' " +
                    "WHERE id='$wuserId'")
            if (query != null) {
                return Message("Successfully Updated")
            }
            return Message("Not Successful")
        }

        override fun userDelete(id: String) {
            val conn = Config().connect()
            val query = conn?.prepareStatement("DELETE FROM users WHERE id=$id")
            if (query != null) {
                Message("Not Successful")
                query.executeUpdate()
            }
             Message("Not Successful")
        }

        override fun getData(id: String): List<User> {
            //procedural
            var data = listOf<User>()
            val conn = Config().connect()
            val query = conn.prepareStatement("SELECT * FROM users WHERE id=$id;")
            var result = query.executeQuery()
                while (result.next()) {
                    // getting the value of the id column
                    var id: Int = result.getInt("id")
                    // getting the value of the employee id column
                    var name= result.getString("name")
                    // getting the value of the meal breakfast column
                    var address= result.getString("address")
                    // getting the value of the meal lunch column
                    var email= result.getString("email")
                    // getting the value of the meal dinner column
                    var password = result.getString("password")
                    data = listOf(User("$id", "$name", "$address", "$email", "$password"))
                }
                if(data.isEmpty()) return listOf(User("", "", "", "", ""))

            return data

        }

        override fun getAllData(): MutableList<User> {

            // the query is only prepared not executed
            //traditional
            //val query = conn.prepareStatement("SELECT * FROM users;")
            // the query is executed and results are fetched
            val data = mutableListOf<User>()
            val conn = Config().connect()
            val query = conn.prepareStatement("SELECT * FROM users;")
            val result = query.executeQuery()
            while (result.next()) {
                // getting the value of the id column
                var id: Int = result.getInt("id")
                // getting the value of the employee id column
                var name= result.getString("name")
                // getting the value of the meal breakfast column
                var address= result.getString("address")
                // getting the value of the meal lunch column
                var email= result.getString("email")
                // getting the value of the meal dinner column
                var password = result.getString("password")
                data.add(User("$id", "$name", "$address", "$email", "$password"))
            }
            return data


            /*  //functional
                    val data = mutableListOf<User>()
                    Config().connect().use {
                            con -> con.prepareStatement("SELECT * FROM users;")
                        .use { result -> result.executeQuery()
                            .use {
                                    datas -> while (datas.next()){
                                // getting the value of the id column
                                var id: Int = datas.getInt("id")
                                // getting the value of the employee id column
                                var name= datas.getString("name")
                                // getting the value of the meal breakfast column
                                var address= datas.getString("address")
                                // getting the value of the meal lunch column
                                var email= datas.getString("email")
                                // getting the value of the meal dinner column
                                var password = datas.getString("password")
                                data.add(User("$id", "$name", "$address", "$email", "$password"))
                            }
                                return data
                            }}
                    }
            */
        }
}
