// Original bug: KT-11914

data class User private constructor(val name: String, val id: Int){
    companion object{
        fun of(name:String, id: Int): User{
            return User(if(name.isEmpty()) "Unknown" else name, id)
        }
    }
}

fun getUser(): User {
    // returns user with empty name, which by default is impossible.
    return User.of("Alex", 1).copy(name="", id=2)
}
