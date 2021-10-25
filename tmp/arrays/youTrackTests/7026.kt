// Original bug: KT-15576

package utils

import kotlin.reflect.KCallable

abstract class Day(inputs: Day.() -> Unit) {
    val inputs = mutableListOf<Pair<String?, String>>()

    init {
        inputs()
    }

    operator fun String.unaryPlus() = +(null to this)
    operator fun Pair<String?, String>.unaryPlus() = inputs.add(this)

    fun res(name: String) = name to readResource(name)

    operator fun invoke() {
        inputs.forEach {
            println(solve(it.second, true))
            println(solve(it.second, false))
        }
    }

    open fun String.split(): List<String> = splitLines()

    open fun solve(input: String, part1: Boolean) = solve(input.split(), part1)
    open fun solve(input: List<String>, part1: Boolean): Any? = ""
}


private class Foo

fun readResource(name: String) = Foo::class.java
        .getResourceAsStream("/" + name)
        .bufferedReader()
        .use { it.readText() }

fun String.splitLines() = split("\\r?\\n".toRegex())
