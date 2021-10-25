// Original bug: KT-35338

package com.xxxxxxx.yyyyyyy.zzzzzzz

class SomeVeryImportantClass {
    val someData: Any? = null

    companion object {
        fun getInstance(o: Any): SomeVeryImportantClass =
            SomeVeryImportantClass()
    }
}
