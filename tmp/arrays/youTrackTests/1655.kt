// Original bug: KT-24193

package test

interface Foo : Cloneable

class Bar : Foo {
    fun createClone(): Bar {
        return this.clone() as Bar
    }
}

fun main(args: Array<String>) {
    println(Bar().createClone())
}
