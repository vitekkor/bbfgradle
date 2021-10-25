// Original bug: KT-25801

package main

import kotlin.reflect.jvm.kotlinFunction

fun main(args: Array<String>) {
        val clazz = Class.forName("example.A")
	val testMethod = clazz.declaredMethods.first()
	val kotlinFn = testMethod.kotlinFunction // throws ArrayStoreException 
	println(kotlinFn?.call(clazz.newInstance()))
}
