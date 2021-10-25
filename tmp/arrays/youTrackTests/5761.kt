// Original bug: KT-20280

class Data(val a: List<String>) {
    override fun toString(): String = a.joinToString()
}


fun main(args: Array<String>) {
    println(Data(listOf("foo", "bar"))) // Do 'Step Into' action here
}

