// Original bug: KT-36307

fun runB(a: () -> Unit) {}

fun <T : Comparable<T>> Array<T>.foo(): Unit = let { runB { bar(it) } } // changes to runB { bar(this) }

fun <T : Comparable<T>> bar(a: Array<T>): Unit {}
