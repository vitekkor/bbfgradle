// Original bug: KT-33997

package com.company

import kotlin.reflect.KClass

//Parent class of components
abstract class AbstractComponent<T : AbstractComponent<T>> {
    //self reference for runtime AOP magic in child
    lateinit var self: T

    abstract fun logic()
}

class Prototype : AbstractComponent<Prototype>() {
    override fun logic() {
        self.methodInAspect()
    }

    fun methodInAspect() {
        println("Hello")
    }
}


fun <T : AbstractComponent<T>> createComponent(componentClass: KClass<T>): T {
    //just example. In my case I have spring.
    val result = componentClass.constructors.first().call()

    result.self = result
    return result
}

fun <T : AbstractComponent<T>> someFun(componentClasses: List<KClass<T>>) {
    for (componentClass in componentClasses) {
        val component = createComponent(componentClass)
        component.logic()
    }
}

fun main() {
    someFun(listOf(Prototype::class))
}
