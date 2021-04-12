// Original bug: KT-28083

open class Foo

class Bar : Foo()

fun findByName(name: String): Foo? = TODO()

private fun findOrCreateBar(name: String): Bar =
    findByName(name)?.let { existing ->
        if (existing !is Bar) error("!!!")
        existing as Bar
    } ?: Bar()
