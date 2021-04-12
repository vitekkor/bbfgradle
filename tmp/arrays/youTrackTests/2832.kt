// Original bug: KT-40507

package org.example.error

suspend fun main() {
    val properties = Properties(username = "John", password = "password")
    val users = App(properties).api.login {
        users()
    }
    println(users)
}

interface API {
    suspend fun <T> login(block: suspend LoggedIn.() -> T): T
    interface LoggedIn {
        suspend fun users(): List<User>
    }
}


class App(properties: Properties) {
    val api = object : API { // to enforce always using the interface instead a explicit class, the implementation is private
            override suspend fun <T> login(block: suspend API.LoggedIn.() -> T): T {
                class LoggedIn(val token: String) : API.LoggedIn {

                    private suspend fun <T : Any> get(
                        url: String,
                        constructor: () -> T
                    ): T { // if this fun is inline, the compiler crashes
                        println("called $url with $token")
                        return constructor()
                    }

                    override suspend fun users() = get("/users") {
                        listOf(User("John Doe"))
                    }
                }
                println("log with ${properties.username} and ${properties.password}")
                val token = "tokenABC"
                println("got token $token")
                return LoggedIn(token).block().also {
                    println("logout")
                }
            }
        }
}

data class Properties(val username: String, val password: String)

data class User(val name: String)
