package den.kotlinspringhttprestapijdbc.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

@Component
@Configuration
class Config {
    @Value("\${spring.datasource.driverClassName}")
    val dbDriver: String? = "org.postgresql.Driver"

    @Value("\${spring.datasource.url}")
    val dbUrl: String? = "jdbc:postgresql://localhost:5432/sampledb"

    @Value("\${spring.datasource.username}")
    val dbUsername: String? = "postgres"

    @Value("\${spring.datasource.password}")
    val dbPassword: String? = "root"

    //Hikari Connection
    fun connect(): Connection {
        // set the jdbcUrl
        val url = "$dbUrl"
        // set the username
        val username = "$dbUsername"
        // set the password
        val password = "$dbPassword"

        return DriverManager.getConnection(url,username,password)
    }
}
