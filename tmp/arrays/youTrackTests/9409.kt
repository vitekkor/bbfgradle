// Original bug: KT-12983

abstract class SomeClassK<T>(val t: T) {
    abstract fun iterate();
}

class OtherClassK(array : DoubleArray) : SomeClassK<DoubleArray>(array){
    override fun iterate() {
        for (i in t.indices) {
            println(i)
        }
    }

}

fun main(args : Array<String>) {
    OtherClassK(DoubleArray(0))
}
