// Original bug: KT-29181

fun main() {
    print(test())
}

fun test(): Int {
    var ints = arrayOf(3, 4, 5, 6)
    var x = 100

    run loop@{
        ints.forEach {
            if (it == 3) {
                x = 10
                return@loop
            }
            println(it)
        }
    }

    return x
}
