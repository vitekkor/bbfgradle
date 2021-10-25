// Original bug: KT-37100

interface X {
    fun other(): String
    fun retrieve(): String
    
    fun forward() = "Simon says: "+retrieve()
}

class XImpl: X {
    override fun other() = "Always the same"
    override fun retrieve() = "From parent - should be overridden!"
}

class A(parent: X): X by parent {
    override fun retrieve() = "From child A"
}

class B(val parent: X): X {
    override fun other() = parent.other()
    override fun retrieve() = "From child B"
}

fun main() {
    val x = XImpl()
    println(A(x).forward())
    println(B(x).forward())
}
