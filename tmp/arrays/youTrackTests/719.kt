// Original bug: KT-43473

package com.molikuner

fun main() {
    println(TestObject["a"].toString())
    //println(TestObject.get("a").toString())
    //println((TestObject["a"] as TestObject).toString())
}

interface ITest {
    operator fun get(key: String, deepCopyResult: Boolean = false): ITest
}

object TestObject: ITest {
    override operator fun get(key: String, deepCopyResult: Boolean): TestObject = this
}
