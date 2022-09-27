package den.kotlinspringhttprestapijdbc.model

data class User(
    var id: String,
    var name: String,
    var address: String,
    var email: String,
    var password: String
)
data class Message (
    val status: String
        )