// Original bug: KT-23354

fun myFunc(): Int {
    val input = "{[]}"
    var garbageCount = 0
    var poc = 0
    var garbage = false

    while (poc < input.length) {

        if (!garbage) {
            val a = input[poc]
            if (a == '<') {
                println("test")
                garbage = true
            }
        } else {
            if (input[poc] == '!') {
                poc++
            } else if (input[poc] == '>') {
                garbage = false
            } else {
                garbageCount++
            }
        }
        poc++

    }

    return input.length-garbageCount
}

fun main(args: Array<String>) {
    myFunc()
}