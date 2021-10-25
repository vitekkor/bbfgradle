// Original bug: KT-19839

open class First {
    companion object {}
} 

class Second : First() {
    companion object {
        var str = " "
        fun f() = print(str)
    }
}

class Aside {
    fun usage() = Second.f()
}
