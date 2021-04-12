// Original bug: KT-30931

    data class Tmp(val list: List<Int>)

    fun `print chunks`() {
        val list = (1..19000).toList()
        val chunks = list.chunked(5000) { Tmp(list = it) }
                .apply { println("chunks amount = ${this.size}}") }
                .forEach { println(it.list.size) }
    }
