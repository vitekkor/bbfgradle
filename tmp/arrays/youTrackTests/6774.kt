// Original bug: KT-11182

class NDArray<T>(val values: Array<T> ){
    operator fun get(i:Int ): T = values[i]
    companion object Factory{
        inline operator fun <reified T>invoke(i: Int, j: Int, noinline init: (Int) -> T) = NDArray(Array(i * j, init))
    }
}

fun main(args: Array<String>){
    val m = NDArray(4,4, {0})  // {0} reference makes the compiler throw a Exception. When reference to ::zero it works ok.
    val s = m[0]
}

fun zero(i:Int ) = 0
