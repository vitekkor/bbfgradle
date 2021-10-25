// Original bug: KT-14426

class Wrapper{
    operator fun<T> invoke(action: () -> T): T {
        println("start wrap")
        val result = action()
        println("end wrap")
        return result
    }
}

fun main(args: Array<String>){
    val wrapper = Wrapper()
    val res = wrapper {
        println("action")
    }
}
