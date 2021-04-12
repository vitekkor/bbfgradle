// Original bug: KT-43943

fun getThree(): Int {
    var i = 0

    while (i++ < 5) {
        when (i) {
            1 -> {
                println("ignoring 1")
            }
            2 -> {
                println("don't like 2 either")
            }
            3 -> {
                break
            }
        }
    }

    return i
}
