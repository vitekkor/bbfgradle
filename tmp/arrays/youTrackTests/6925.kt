// Original bug: KT-20460

package com.company

fun main(args: Array<String>) {
    val list = listOf(1, 2, 3)
    val test = object : Test(list) {} // List isn't expected to be saved in test instance
    val my = SubTest(list) // Creating explicit class is a workaround
    println("$test $my")
}

class SubTest(a: List<Int>): Test(a)

open class Test(a: List<Int>)
