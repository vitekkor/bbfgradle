// Original bug: KT-45932

import kotlin.test.assertEquals

fun main(args: Array<String>) {
    val regex = "(?<=^/nl(?:/nl)?/\\d{1,600}[\\d+]{0,600}/[\\d+]{0,600})(\\d+)".toRegex()
    val path = "/nl/nl/1+2/3+4/"

    val values = regex.findAll(path).mapNotNull {
        it.groups.last()?.value
    }.toList()

    assertEquals(2, values.count())
    assertEquals("3", values[0])
    assertEquals("4", values[1])
}    
