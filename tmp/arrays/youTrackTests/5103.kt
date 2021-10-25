// Original bug: KT-33997

package com.company

import kotlin.reflect.KClass


//Parent class of components
abstract class AbstractComponent<T : AbstractComponent<T>> {
    //self reference for runtime AOP magic in child
    lateinit var self: T

    abstract fun logic()
}

class Prototype:AbstractComponent<Prototype>(){
    override fun logic(){
        self.methodInAspect()
    }
    fun methodInAspect(){
        println("Hello")
    }
}


fun <T : AbstractComponent<T>> createComponent(componentClass: KClass<T>):T {
    //just example. In my case I have spring.
    val result =  componentClass.constructors.first().call()

    result.self=result
    return result
}


fun someFun(componentClasses: List<KClass<out AbstractComponent<*>>>){
    for (componentClass in componentClasses) {
        //        createComponent(componentClass) does not compile because AbstractComponent is not T: AbstractComponent<T>
        class WorkAround:AbstractComponent<WorkAround>() {
            override fun logic() {
                //Do nothing
            }
        }

        //Dirty hack with type erasure to hack compiler check
        //in runtime it is ok, because componentClass is KClass<Any> and KClass<WorkAround> is KClass<Any>
        val component: AbstractComponent<*> = createComponent( componentClass as KClass<WorkAround>)
        component.logic()
    }
}
fun main() {
    someFun(listOf(Prototype::class))
}
