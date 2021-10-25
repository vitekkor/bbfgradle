// Original bug: KT-10729

package com.vladsch.kotlin

class TestStaticToCompanionPrivate {
    companion object {
        private val amPrivate:Int = 10

        @JvmStatic fun amStatic():Int {
            return amPrivate
        }
    }
}
