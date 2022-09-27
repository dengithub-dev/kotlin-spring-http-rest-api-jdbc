package den.kotlinspringhttprestapijdbc.controller

import den.kotlinspringhttprestapijdbc.config.*
import den.kotlinspringhttprestapijdbc.model.*
import den.kotlinspringhttprestapijdbc.service.UserService
import org.springframework.web.bind.annotation.*
import java.sql.Connection

@RestController
class Controller (var Userdatas: UserService) {
    @GetMapping("/users")
    fun getUsers(): MutableList<User>? {
        return Userdatas.getAllData()
    }
    @GetMapping("/user/{id}")
    fun getUser(@PathVariable id: String): List<User> {
        return Userdatas.getData(id)
    }

    @PostMapping("/user")
    fun createUser(@RequestBody UserDetails: User) {
        val (id, name, address, email, password) = UserDetails
        Userdatas.createUser(id, name, address, email,password)
    }
    @PutMapping("/user/{id}")
    fun updateUser(@PathVariable id: String, @RequestBody UserDetails: User){
        val (uid, name, address, email, password) = UserDetails
        Userdatas.updateUser(id,uid,name,address,email,password)
    }
    @DeleteMapping("user/{id}")
    fun deleteUser(@PathVariable id:String) {
        Userdatas.userDelete(id)
    }

}