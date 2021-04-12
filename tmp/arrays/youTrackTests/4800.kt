// Original bug: KT-35479

import kotlin.reflect.*

fun x(arg: Int): String = arg.toString()

fun main() {
    val refx = ::x
    val crefx: KCallable<*> = refx
    println(refx is KFunction1<*, *>)  // prints true, expected true
    println(refx is KFunction0<*>)  // prints true, expected false
    println(refx is KFunction<*>) // prints false, expected true, gives "Check for instance is always 'true'" warning
    println(refx is KCallable<*>) // prints false, expected true, gives "Check for instance is always 'true'" warning
    println(crefx is KCallable<*>) // prints false, expected true, gives "Check for instance is always 'true'" warning
}
