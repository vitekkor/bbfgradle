// Original bug: KT-14966

interface I {
    val resolution: String
        get() = "OK"
}

class C : I {
    override val resolution: String by lazy { super.resolution }
}

fun main(args: Array<String>) {
    println(C().resolution)
}
