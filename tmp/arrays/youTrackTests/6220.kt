// Original bug: KT-21796

fun main(args: Array<String>) {
    var a : Long? = 1
    var b : Long? = 2
    var c : Long? = null
    a?.let {
        print("a")
        b?.let {                        // (**)
            print("b")
            c?.let {                    // (*)
                print("c")
            }
        } ?: throw Exception("This should not be thrown!")
    }
}
