// Original bug: KT-35017

package com.example

public fun main(args: Array<String>) {    
    ByLazy()
    println(args)
}

class ByLazy() : ByLazySuper() {
    private val s: String by lazy { "" }
    
    override fun test() {
        println(s)
    }
}

abstract class ByLazySuper() {

    init {
        test()
    }

    abstract fun test()
}
