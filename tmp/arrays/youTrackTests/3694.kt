// Original bug: KT-23142

import java.util.*

fun main(args: Array<String>) {
    val test = generateLine()

    var time = System.currentTimeMillis()
    val hash1 = test.toHashSet()
    val t1 = System.currentTimeMillis() - time

    time = System.currentTimeMillis()
    val hash2 = hashSetOf<Char>()
    test.forEach {
        hash2.add(it)
    }
    val t2 = System.currentTimeMillis() - time

    time = System.currentTimeMillis()
    val hash3 = hashSetOf<Char>()
    test.forEach {
        if (!hash3.contains(it)) {
            hash3.add(it)
        }
    }
    val t3 = System.currentTimeMillis() - time

    println("t1 = $t1 t2 = $t2 t3 = $t3")
}

private fun generateLine() : String {
    val rand = Random()
    val buff = StringBuilder()
    do {
        val value = rand.nextInt()
        buff.append(value)
    } while (buff.length < 1000000)
    return buff.toString()
}
