// Original bug: KT-16724

import Thing.ThingBuilder
import Thing.ThingBuilder.*

fun main(args: Array<String>) {
    val foo0: Thing = ThingBuilder.setB(2).setA(1).setC(3).build()
    println(foo0)
    val foo1: Thing = ThingBuilder.setA(1).setB(2).setC(3).build()
    println(foo1)
//    val foo2: Thing = Thing.setA(1).setB(2).build() // Unavailable
//    println(foo2)
}

class Thing internal constructor(private val a: Int, private val b: Int, private val c: Int) {

    override fun toString(): String {
        return "a = $a, b = $b, c = $c"
    }

    open class ThingBuilder<A, B, C> private constructor(internal var a: Int, internal var b: Int, internal var c: Int) {
        abstract class OK
        abstract class NO

        fun setA(a: Int): ThingBuilder<OK, B, C> {
            this.a = a
            return ThingBuilder(a, this.b, this.c)
        }

        fun setB(b: Int): ThingBuilder<A, OK, C> {
            this.b = b
            return ThingBuilder(this.a, b, this.c)
        }

        fun setC(c: Int): ThingBuilder<A, B, OK> {
            this.c = c
            return ThingBuilder(this.a, this.b, this.c)
        }

        companion object : ThingBuilder<NO, NO, NO>(0, 0, 0)
    }
}

fun ThingBuilder<OK, OK, OK>.build() = Thing(this.a, this.b, this.c)
