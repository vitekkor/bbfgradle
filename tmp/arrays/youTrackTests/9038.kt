// Original bug: KT-16929

class Generic<P : Any>(val p: P)

class Host {
    fun t() {
        System.out.println("T")
    }
}

fun main(args: Array<String>) {
    (Generic(Host()).p::t)()
}
