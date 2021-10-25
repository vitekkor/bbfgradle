// Original bug: KT-38627

package com.xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy.zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz

class SomeVeryImportantClass {
    fun getSomeData(): Any? = null

    companion object {
        fun getInstance(o: Any): SomeVeryImportantClass =
            SomeVeryImportantClass()
    }
}
