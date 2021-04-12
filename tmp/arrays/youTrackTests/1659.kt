// Original bug: KT-25850

package com.packageA

abstract class A {
    protected var x: String? = null
        private set // important that setter is private


    open fun set() {
        x = "not_important"
    }
}
