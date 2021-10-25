// Original bug: KT-38702

open class Base(open val name : String){
    init {
        println("initializing base with name = ${this.name}") // Accessing non-final property name in constructor
    }
    open val size : Int = name.length // Accessing non-final property name in constructor
    fun getNa() : String {
        return name
    }
}
class Derived constructor(name: String, val lastName : String) : Base(name){
    override val name = name // Accessing non-final property name in constructor
    override val size : Int = (name.length + lastName.length).also {
        println("Initialzing derived size with $it")
    }
}
fun main(){
    val derived = Derived("Tarun", "Chawla")
    println(derived.getNa())
}
